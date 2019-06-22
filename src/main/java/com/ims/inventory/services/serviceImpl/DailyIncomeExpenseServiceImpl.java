package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.ims.inventory.dtos.CustomDtos.ManagementRequestDto;
import com.ims.inventory.dtos.SearchResponseDto;
import com.ims.inventory.models.*;
import com.ims.inventory.models.QPurchaseOrder;
import com.ims.inventory.models.QSales;
import com.ims.inventory.repositories.PurchaseOrderRepository;
import com.ims.inventory.repositories.SalesRepository;
import com.ims.inventory.services.DailyIncomeExpenseService;
import com.ims.inventory.utils.Consts;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DailyIncomeExpenseServiceImpl implements DailyIncomeExpenseService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    SalesRepository salesRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar calendar = Calendar.getInstance();

    @Override
    public SearchResponseDto load(ManagementRequestDto managementRequestDto) {
        Double totalIncome = 0.00;
        Double totalExpenses = 0.00;
        SearchResponseDto searchResponseDto = new SearchResponseDto();

        if  (managementRequestDto.getCompany() != null&& managementRequestDto.getCompany().getId() != null)  {
            QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
            QSales qSales = QSales.sales;
            Date fromDate = new Date();
            Date toDate = new Date();

            try {
                calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(managementRequestDto.getFromDate()).concat(" 00:00:00")));
                fromDate = calendar.getTime();
                calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(managementRequestDto.getToDate()).concat(" 23:59:59")));
                toDate = calendar.getTime();
            } catch (Exception e){
                e.printStackTrace();
            }

            BooleanBuilder booleanBuilderPO = new BooleanBuilder();
            booleanBuilderPO.and(qPurchaseOrder.createdDate.between(fromDate,toDate));
            booleanBuilderPO.and(qPurchaseOrder.company.id.eq(managementRequestDto.getCompany().getId()));
            BooleanBuilder salesOrder = new BooleanBuilder();
            salesOrder.and(qSales.saleDate.between(fromDate,toDate));
            salesOrder.and(qSales.company.id.eq(managementRequestDto.getCompany().getId()));

            HashMap<String, Object> purchaseOrder = new HashMap<>();
            List<PurchaseOrder> purchaseOrders = Lists.newArrayList(purchaseOrderRepository.findAll(booleanBuilderPO));
            List<PurchaseOrderItem> purchaseOrderItems = new ArrayList<>();
            List<SalesOrderItem> salesOrderItems = new ArrayList<>();
            List<Sales> salesList =  Lists.newArrayList(salesRepository.findAll(salesOrder));

            for (Sales sales1: salesList) {
                totalIncome = totalIncome + sales1.getOrderAmount();
                salesOrderItems.addAll(sales1.getSalesOrderItems());
            }

            for (PurchaseOrder purchaseOrder1: purchaseOrders) {
                totalExpenses = totalExpenses + purchaseOrder1.getTotalAmount();
                purchaseOrderItems.addAll(purchaseOrder1.getPurchaseOrderItems());
            }

            List<HashMap<String, Object>> orderItemGroup = new ArrayList<>();
            for (SalesOrderItem salesOrderItem: salesOrderItems) {


                HashMap<String, Object> existObj = orderItemGroup.stream().filter(item -> item.get("productId") == salesOrderItem.getProduct().getId()).findAny().orElse(null);
                if (existObj != null) {
                    HashMap<String, Object> orderObj2 = orderItemGroup.stream().filter(itemId -> itemId.get("productId") == salesOrderItem.getProduct().getId()).findAny().orElse(null);
                    orderObj2.replace("totalIncome", (Double)orderObj2.get("totalIncome") + salesOrderItem.getSubTotal());
                    orderObj2.replace("totalCount", (Integer)orderObj2.get("totalCount") + salesOrderItem.getQuantity());

                } else {
                    HashMap<String, Object> orderObj = new HashMap<>();
                    orderObj.put("productId", salesOrderItem.getProduct().getId());
                    orderObj.put("productName", salesOrderItem.getProduct().getProductName());
                    orderObj.put("totalCount", salesOrderItem.getQuantity());
                    orderObj.put("totalIncome", salesOrderItem.getSubTotal());
                    orderItemGroup.add(orderObj);
                }
            }

            HashMap<String, Object> incomeObject = new HashMap<>();
            incomeObject.put("incomeItems",orderItemGroup);
            incomeObject.put("totalIncome",totalIncome);
            purchaseOrder.put("income",incomeObject);
            List<HashMap<String, Object>> orderItemGroup2 = new ArrayList<>();

            for (PurchaseOrderItem purchaseOrderItem: purchaseOrderItems) {
                HashMap<String, Object> existObj = orderItemGroup2.stream().filter(item -> item.get("productId") == purchaseOrderItem.getProduct().getId()).findAny().orElse(null);
                if (existObj != null) {
                    HashMap<String, Object> orderObj2 = orderItemGroup2.stream().filter(itemId -> itemId.get("productId") == purchaseOrderItem.getProduct().getId()).findAny().orElse(null);
                    orderObj2.replace("totalIncome", (Double)orderObj2.get("totalIncome") + purchaseOrderItem.getSubTotal());
                    orderObj2.replace("totalCount", (Integer)orderObj2.get("totalCount") + purchaseOrderItem.getQuantity());

                } else {
                    HashMap<String, Object> orderObj = new HashMap<>();
                    orderObj.put("productId", purchaseOrderItem.getProduct().getId());
                    orderObj.put("productName", purchaseOrderItem.getProduct().getProductName());
                    orderObj.put("totalCount", purchaseOrderItem.getQuantity());
                    orderObj.put("totalIncome", purchaseOrderItem.getSubTotal());
                    orderItemGroup2.add(orderObj);
                }
            }

            HashMap<String, Object> expenseObject = new HashMap<>();
            expenseObject.put("expenseItems",orderItemGroup2);
            expenseObject.put("totalExpense",totalExpenses);
            purchaseOrder.put("expense",expenseObject);
            searchResponseDto.setSearchObject(purchaseOrder);

            searchResponseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            searchResponseDto.setStatus(Consts.JOB_FAILED);
        }

        return searchResponseDto;
    }
}

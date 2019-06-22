package com.ims.inventory.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CodeGenerator {

    public static String generateCode(String type, Long id){
        SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MM");

        String prefix = null;

        if (type.equals(Consts.GEN_TYPE_SALES)) {
            prefix = Consts.SALES_CODE_PREFIX;
        }
        else if (type.equals(Consts.GEN_TYPE_CATEGORY)) {
            prefix = Consts.CATEGORY_CODE_PREFIX;
        }
        else if (type.equals(Consts.GEN_TYPE_CUSTOMER)) {
            prefix = Consts.CUSTOMER_CODE_PREFIX;
        }
        else if (type.equals(Consts.GEN_TYPE_PO)) {
            prefix = Consts.PO_CODE_PREFIX;
        }
        else if (type.equals(Consts.GEN_TYPE_PRODUCT)) {
            prefix = Consts.PRODUCT_CODE_PREFIX;
        }
        else if (type.equals(Consts.GEN_TYPE_SUPPLIER)) {
            prefix = Consts.SUPPLIER_CODE_PREFIX;
        }
        else {
            prefix = "";
        }

        return prefix.concat("-")
                .concat(dateFormatYear.format(new Date()))
                .concat("-")
                .concat(dateFormatMonth.format(new Date()))
                .concat("-")
                .concat(String.format("%05d", id));
    }


}

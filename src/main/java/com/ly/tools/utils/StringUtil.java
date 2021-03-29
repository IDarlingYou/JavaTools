package com.ly.tools.utils;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author: LY
 * @Date: 2021/3/29 10:20
 * @Description:
 **/
public class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isNotBlank(Object... objs) {
        return isNotEmpty(objs);
    }

    public static boolean isBlank(Object obj) {
        return isEmpty(obj);
    }

//    public static boolean sameNum(String str) {
//        Pattern p = Pattern.compile("([\\d])\\1{2}");
//        Matcher m = p.matcher(str);
//        return m.find();
//    }

    public static boolean validatorName(String name) {
        if (name != null && name.length() <= 20 && name.length() >= 2) {
            String reg = "^[一-龥]{2,20}(?:·[一-龥]{1,20})*$";
            return name.matches(reg);
        } else {
            return false;
        }
    }

    public static boolean validator(String id) {
        if (id != null && (id.length() == 15 || id.length() == 18)) {
            String str = "[1-9]{2}[0-9]{4}(19|20)[0-9]{2}((0[1-9]{1})|(1[0-2]{1}))((0[1-9]{1})|([1-2]{1}[0-9]{1}|(3[0-1]{1})))[0-9]{3}[0-9xX]{1}";
            Pattern pattern = Pattern.compile(str);
            return pattern.matcher(id).matches();
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(Object obj) {
        if (obj == null) {
            return false;
        } else {
            String json;
            if (obj instanceof String) {
                json = (String) obj;
                if ("".equals(json)) {
                    return false;
                }
            } else {
                if (obj instanceof Map) {
                    return ((Map) obj).size() != 0;
                }
                if (obj instanceof Collection) {
                    return !((Collection) obj).isEmpty();
                }
            }
            return true;
        }
    }

    public static boolean isEmpty(Object obj) {
        return !isNotEmpty(obj);
    }

    public static boolean isNotEmpty(Object... objs) {
        if (objs != null && objs.length != 0) {
            Object[] var1 = objs;
            int var2 = objs.length;
            for (int var3 = 0; var3 < var2; ++var3) {
                Object obj = var1[var3];
                if (!isNotEmpty(obj)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}

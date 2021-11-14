package org.jiangxin.gpu.udf;

import org.apache.flink.table.functions.ScalarFunction;

import org.jiangxin.gpu.jna.CLibrary;

public class NativeUdf extends ScalarFunction {

    public String eval(String s) {
        return String.valueOf(CLibrary.INSTANCE.main());
    }
}
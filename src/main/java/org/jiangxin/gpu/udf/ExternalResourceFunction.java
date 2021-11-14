package org.jiangxin.gpu.udf;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;

import org.jiangxin.gpu.jna.CLibrary;

public class ExternalResourceFunction extends RichMapFunction<Integer, String> {

    private final int seq;
    public ExternalResourceFunction(int seq) {
        this.seq = seq;
    }

    private static final String RESOURCE_NAME = "gpu";
    private int gpuIndex = 0;

    @Override
    public void open(Configuration parameters) {
//        Set<ExternalResourceInfo> externalResourceInfos = getRuntimeContext().getExternalResourceInfos(RESOURCE_NAME);
//        Preconditions.checkState(!externalResourceInfos.isEmpty(), "I need at least one GPU device while finding 0 GPU.");
//        final Optional<String> firstIndexOptional = externalResourceInfos.iterator().next().getProperty("index");
//        Preconditions.checkState(firstIndexOptional.isPresent());
//        gpuIndex = Integer.parseInt(firstIndexOptional.get());
    }

    @Override
    public String map(Integer value) {
        return String.valueOf(CLibrary.INSTANCE.main());
    }
}
package com.github.grundygolem2.postprocessor;


import java.util.List;

public interface PostProcessor<T> {
    boolean postProcess(List<T> cards);
}

package org.weka.web.application.service;

import weka.core.Instances;

import java.util.List;

/**
 * Created by suad on 10/25/2014.
 */
public interface SMOService {


    List<String> GetAttributes(String fileName) throws Exception;

    List<String> trainFile(String fileName, String testFileName) throws Exception;

    List<String> crossValidateFile(String fileName) throws Exception;

    Instances getInstances(String fileName) throws Exception;
}

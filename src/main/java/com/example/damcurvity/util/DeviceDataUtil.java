package com.example.damcurvity.util;

import com.example.damcurvity.entity.InfoStationNode;
import org.apache.commons.math3.filter.DefaultMeasurementModel;
import org.apache.commons.math3.filter.DefaultProcessModel;
import org.apache.commons.math3.filter.KalmanFilter;
import org.apache.commons.math3.filter.ProcessModel;
import org.apache.commons.math3.filter.MeasurementModel;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceDataUtil {

//    private static Map<String, List<InfoStationNode>> groupInfoStationNodesByNodeIdAndTimeInterval(List<InfoStationNode> InfoStationNodes, Long timeInterval) {
//        Map<Integer, List<InfoStationNode>> groupedInfoStationNodes = new HashMap<>();
//        Map<String, List<InfoStationNode>> result = new HashMap<>();
//        for (InfoStationNode dp : InfoStationNodes) {
//            Integer nodeId = dp.getNodeId();
//
//            // 检查是否已经存在该节点的数据组
//            if (groupedInfoStationNodes.containsKey(nodeId)) {
//                // 如果存在，将数据点添加到该组中
//                List<InfoStationNode> dataGroup = groupedInfoStationNodes.get(nodeId);
//                dataGroup.add(dp);
//            } else {
//                // 如果不存在，创建新的节点数据组并添加数据点
//                List<InfoStationNode> dataGroup = new ArrayList<>();
//                dataGroup.add(dp);
//
//                groupedInfoStationNodes.put(nodeId, dataGroup);
//            }
//        }
//
//        for (Map.Entry<Integer, List<InfoStationNode>> entry : groupedInfoStationNodes.entrySet()) {
//            Integer nodeId = entry.getKey();
//            List<InfoStationNode> infoStationNodeList = entry.getValue();
//            long stepTime = 0L;
//            for(InfoStationNode infoStationNode:infoStationNodeList)
//            {
//                if(infoStationNode.getTime()>stepTime)
//                {
//                    stepTime = infoStationNode.getTime().getTime()+timeInterval;
//                    List<InfoStationNode> temp = new ArrayList<>();
//                    result.put(nodeId.toString()+stepTime,temp);
//                    temp.add(infoStationNode);
//                }
//                else
//                {
//                    result.get(nodeId.toString()+stepTime).add(infoStationNode);
//                }
//            }
//        }
//
//        return result;
//    }

    private static List<InfoStationNode> correctValues(List<InfoStationNode> InfoStationNodes, Double confidenceIntervalMultiplier) {
        List<InfoStationNode> result = new ArrayList<>();
//        Map<String, List<InfoStationNode>> groupedNodes = groupInfoStationNodesByNodeIdAndTimeInterval(InfoStationNodes, timeInterval);
//        for (Map.Entry<String, List<InfoStationNode>> entry : groupedNodes.entrySet()) {
        double xValSum = 0;
        double yValSum = 0;
        double zValSum = 0;
        double xAccSum = 0;
        double yAccSum = 0;
        double zAccSum = 0;
        int dpCount = 0;

        for (InfoStationNode currDp : InfoStationNodes) {
            xValSum += currDp.getXVal();
            yValSum += currDp.getYVal();
            zValSum += currDp.getZVal();
            xAccSum += currDp.getXAcc();
            yAccSum += currDp.getYAcc();
            zAccSum += currDp.getZAcc();
            dpCount++;

        }

        double xValMean = xValSum / dpCount;
        double yValMean = yValSum / dpCount;
        double zValMean = zValSum / dpCount;
        double xAccMean = xAccSum / dpCount;
        double yAccMean = yAccSum / dpCount;
        double zAccMean = zAccSum / dpCount;
        double xValSumZ = 0;
        double yValSumZ = 0;
        double zValSumZ = 0;
        double xAccSumZ = 0;
        double yAccSumZ = 0;
        double zAccSumZ = 0;
        for (InfoStationNode currDp : InfoStationNodes) {
            xValSumZ += Math.pow(currDp.getXVal()-xValMean,2);
            yValSumZ += Math.pow(currDp.getYVal()-yValMean,2);
            zValSumZ += Math.pow(currDp.getZVal()-zValMean,2);
            xAccSumZ += Math.pow(currDp.getXAcc()-xAccMean,2);
            yAccSumZ += Math.pow(currDp.getYAcc()-yAccMean,2);
            zAccSumZ += Math.pow(currDp.getZAcc()-zAccMean,2);
        }
        double xValConfidenceInterval = confidenceIntervalMultiplier * Math.sqrt(xValSumZ / dpCount);
        double yValConfidenceInterval = confidenceIntervalMultiplier * Math.sqrt(yValSumZ / dpCount);
        double zValConfidenceInterval = confidenceIntervalMultiplier * Math.sqrt(zValSumZ / dpCount);
        double xAccConfidenceInterval = confidenceIntervalMultiplier * Math.sqrt(xAccSumZ / dpCount);
        double yAccConfidenceInterval = confidenceIntervalMultiplier * Math.sqrt(yAccSumZ / dpCount);
        double zAccConfidenceInterval = confidenceIntervalMultiplier * Math.sqrt(zAccSumZ / dpCount);
        for (InfoStationNode currDp : InfoStationNodes) {
            if (Math.abs(currDp.getXVal() - xValMean) > xValConfidenceInterval) {
                currDp.setXVal(xValMean);
            }
            if (Math.abs(currDp.getYVal() - yValMean) > yValConfidenceInterval) {
                currDp.setYVal(yValMean);
            }
            if (Math.abs(currDp.getZVal() - zValMean) > zValConfidenceInterval) {
                currDp.setZVal(zValMean);
            }
            if (Math.abs(currDp.getXAcc() - xAccMean) > xAccConfidenceInterval) {
                currDp.setXAcc(xAccMean);
            }
            if (Math.abs(currDp.getYAcc() - yAccMean) > yAccConfidenceInterval) {
                currDp.setYAcc(yAccMean);
            }
            if (Math.abs(currDp.getZAcc() - zAccMean) > zAccConfidenceInterval) {
                currDp.setZAcc(zAccMean);
            }
            result.add(currDp);
        }



        return result;
    }


//    private static List<InfoStationNode> KalmanFilterExample(List<InfoStationNode> InfoStationNodes, Long timeInterval) {
//        List<InfoStationNode> result = new ArrayList<>();
//        Map<String, List<InfoStationNode>> groupedNodes = groupInfoStationNodesByNodeIdAndTimeInterval(InfoStationNodes, timeInterval);
//
//        for (Map.Entry<String, List<InfoStationNode>> entry : groupedNodes.entrySet()) {
//            List<InfoStationNode> infoStationNodeList = entry.getValue();
//            // 定义卡尔曼滤波器的初始状态
//
//            // 定义卡尔曼滤波器的过程模型和测量模型
//            ProcessModel processModel = new InfoStationProcessModel();
//
//            MeasurementModel measurementModel = new InfoStationMeasurementModel();
//            // 创建卡尔曼滤波器
//            KalmanFilter kalmanFilter = new KalmanFilter(processModel, measurementModel);
//
//
//            // 对每个字段进行卡尔曼滤波
//            for (InfoStationNode node : infoStationNodeList) {
//                // 对xVal字段进行卡尔曼滤波
//                kalmanFilter.predict();
//                kalmanFilter.correct(new double[]{node.getXVal()});
//                node.xVal = kalmanFilter.getStateEstimation()[0];
//
//                // 对yVal字段进行卡尔曼滤波
//                kalmanFilter.predict();
//                kalmanFilter.correct(new double[]{node.getYVal()});
//                node.yVal = kalmanFilter.getStateEstimation()[0];
//
//                // 对zVal字段进行卡尔曼滤波
//                kalmanFilter.predict();
//                kalmanFilter.correct(new double[]{node.getZVal()});
//                node.zVal = kalmanFilter.getStateEstimation()[0];
//
//                // 对xAcc字段进行卡尔曼滤波
//                kalmanFilter.predict();
//                kalmanFilter.correct(new double[]{node.xAcc});
//                node.xAcc = kalmanFilter.getStateEstimation()[0];
//
//                // 对yAcc字段进行卡尔曼滤波
//                kalmanFilter.predict();
//                kalmanFilter.correct(new double[]{node.yAcc});
//                node.yAcc = kalmanFilter.getStateEstimation()[0];
//
//                // 对zAcc字段进行卡尔曼滤波
//                kalmanFilter.predict();
//                kalmanFilter.correct(new double[]{node.zAcc});
//                node.zAcc = kalmanFilter.getStateEstimation()[0];
//                result.add(node);
//            }
//        }
//        return result;
//    }

}
//
//class InfoStationProcessModel implements ProcessModel {
//    @Override
//    public RealVector getInitialStateEstimate() {
//        return new ArrayRealVector(new double[]{0, 0, 0, 0, 0, 0});
//    }
//
//    @Override
//    public RealMatrix getInitialErrorCovariance() {
//        return MatrixUtils.createRealIdentityMatrix(6);
//    }
//
//    @Override
//    public RealMatrix getStateTransitionMatrix() {
//        // 状态转移矩阵为单位矩阵
//        return MatrixUtils.createRealIdentityMatrix(6);
//    }
//
//    @Override
//    public RealMatrix getControlMatrix() {
//        return null;
//    }
//
//    @Override
//    public RealMatrix getProcessNoise() {
//        // 过程噪声为零
//        return MatrixUtils.createRealIdentityMatrix(6).scalarMultiply(0);
//    }
//}
//
//class InfoStationMeasurementModel implements MeasurementModel {
//    @Override
//    public RealMatrix getMeasurementMatrix() {
//        // 测量矩阵为单位矩阵
//        return MatrixUtils.createRealIdentityMatrix(1);
//    }
//
//    @Override
//    public RealMatrix getMeasurementNoise() {
//        // 测量噪声为零
//        return MatrixUtils.createRealIdentityMatrix(1).scalarMultiply(0);
//    }

//}

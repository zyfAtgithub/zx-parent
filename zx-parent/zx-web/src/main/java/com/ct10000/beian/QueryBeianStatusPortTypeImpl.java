
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.ct10000.beian;

import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF (incubator) 2.0.4-incubator
 * Fri Jul 14 11:21:24 CST 2017
 * Generated source version: 2.0.4-incubator
 * 
 */

@javax.jws.WebService(name = "QueryBeianStatusPortType", serviceName = "queryBeianStatus",
                      portName = "queryBeianStatusHttpPort",
                      targetNamespace = "http://beian.ct10000.com/queryBeianStatus", 
//                      wsdlLocation = "file:queryBeianStatus.wsdl" ,
		      endpointInterface = "com.ct10000.beian.QueryBeianStatusPortType")
                      
public class QueryBeianStatusPortTypeImpl implements QueryBeianStatusPortType {

    private static final Logger LOG = Logger.getLogger(QueryBeianStatusPortTypeImpl.class.getName());

    /* (non-Javadoc)
     * @see com.ct10000.beian.QueryBeianStatusPortType#jrdQuerybeianstatus(java.lang.Long  jrdId ,)java.lang.String  userName ,)java.lang.String  randVal ,)long  time ,)int  hashAlgorithm ,)int  queryConditionType ,)java.lang.String  queryCondition ,)java.lang.String  sign )*
     */
    public java.lang.String jrdQuerybeianstatus(java.lang.Long jrdId,java.lang.String userName,java.lang.String randVal,long time,int hashAlgorithm,int queryConditionType,java.lang.String queryCondition,java.lang.String sign) { 
        LOG.info("Executing operation jrdQuerybeianstatus");
        System.out.println(jrdId);
        System.out.println(userName);
        System.out.println(randVal);
        System.out.println(time);
        System.out.println(hashAlgorithm);
        System.out.println(queryConditionType);
        System.out.println(queryCondition);
        System.out.println(sign);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}

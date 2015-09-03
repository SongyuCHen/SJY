
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package nju.software.sjy.webservice.cxf;

import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-05-28T16:56:12.321+08:00
 * Generated source version: 3.0.4
 * 
 */

@javax.jws.WebService(
                      serviceName = "SzftWebServiceService",
                      portName = "SzftWebServicePort",
                      targetNamespace = "http://szft.tdh/",
                      wsdlLocation = "http://200.100.48.28:81/court/service/SzftWebService?wsdl",
                      endpointInterface = "nju.software.sjy.webservice.cxf.SzftWebService")
                      
public class SzftWebServicePortImpl implements SzftWebService {

    private static final Logger LOG = Logger.getLogger(SzftWebServicePortImpl.class.getName());

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getJzMl(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXML )*
     */
    public java.lang.String getJzMl(java.lang.String userid,java.lang.String pwd,java.lang.String requestXML) { 
        LOG.info("Executing operation getJzMl");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getPlKt(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXML )*
     */
    public java.lang.String getPlKt(java.lang.String userid,java.lang.String pwd,java.lang.String requestXML) { 
        LOG.info("Executing operation getPlKt");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getFt(java.lang.String  uid ,)java.lang.String  pass ,)java.lang.String  xml )*
     */
    public java.lang.String getFt(java.lang.String uid,java.lang.String pass,java.lang.String xml) { 
        LOG.info("Executing operation getFt");
        System.out.println(uid);
        System.out.println(pass);
        System.out.println(xml);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getAllSjy(java.lang.String  uid ,)java.lang.String  pass ,)java.lang.String  resultXML )*
     */
    public void getAllSjy(java.lang.String uid,java.lang.String pass,javax.xml.ws.Holder<java.lang.String> resultXML) { 
        LOG.info("Executing operation getAllSjy");
        System.out.println(uid);
        System.out.println(pass);
        System.out.println(resultXML.value);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getHjm(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXML )*
     */
    public java.lang.String getHjm(java.lang.String userid,java.lang.String pwd,java.lang.String requestXML) { 
        LOG.info("Executing operation getHjm");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#saveYdktPqxx(java.lang.String  uid ,)java.lang.String  pwd ,)java.lang.String  xml )*
     */
    public java.lang.String saveYdktPqxx(java.lang.String uid,java.lang.String pwd,java.lang.String xml) { 
        LOG.info("Executing operation saveYdktPqxx");
        System.out.println(uid);
        System.out.println(pwd);
        System.out.println(xml);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#sendKtxxZj(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXml )*
     */
    public java.lang.String sendKtxxZj(java.lang.String userid,java.lang.String pwd,java.lang.String requestXml) { 
        LOG.info("Executing operation sendKtxxZj");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXml);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getZzjg(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXML )*
     */
    public java.lang.String getZzjg(java.lang.String userid,java.lang.String pwd,java.lang.String requestXML) { 
        LOG.info("Executing operation getZzjg");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getAjfl(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXML )*
     */
    public java.lang.String getAjfl(java.lang.String userid,java.lang.String pwd,java.lang.String requestXML) { 
        LOG.info("Executing operation getAjfl");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getRy(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXML )*
     */
    public java.lang.String getRy(java.lang.String userid,java.lang.String pwd,java.lang.String requestXML) { 
        LOG.info("Executing operation getRy");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getPlAj(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXML )*
     */
    public java.lang.String getPlAj(java.lang.String userid,java.lang.String pwd,java.lang.String requestXML) { 
        LOG.info("Executing operation getPlAj");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getSjyGzl(java.lang.String  uid ,)java.lang.String  pass ,)java.lang.String  resultXML )*
     */
    public void getSjyGzl(java.lang.String uid,java.lang.String pass,javax.xml.ws.Holder<java.lang.String> resultXML) { 
        LOG.info("Executing operation getSjyGzl");
        System.out.println(uid);
        System.out.println(pass);
        System.out.println(resultXML.value);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#tszbZtfk(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXml )*
     */
    public java.lang.String tszbZtfk(java.lang.String userid,java.lang.String pwd,java.lang.String requestXml) { 
        LOG.info("Executing operation tszbZtfk");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXml);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#sendKtxx(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXml )*
     */
    public java.lang.String sendKtxx(java.lang.String userid,java.lang.String pwd,java.lang.String requestXml) { 
        LOG.info("Executing operation sendKtxx");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXml);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getGxajByZdsj(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXML )*
     */
    public java.lang.String getGxajByZdsj(java.lang.String userid,java.lang.String pwd,java.lang.String requestXML) { 
        LOG.info("Executing operation getGxajByZdsj");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#delKtxx(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXML )*
     */
    public java.lang.String delKtxx(java.lang.String userid,java.lang.String pwd,java.lang.String requestXML) { 
        LOG.info("Executing operation delKtxx");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getTsbl(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXML )*
     */
    public java.lang.String getTsbl(java.lang.String userid,java.lang.String pwd,java.lang.String requestXML) { 
        LOG.info("Executing operation getTsbl");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getDsr(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXML )*
     */
    public java.lang.String getDsr(java.lang.String userid,java.lang.String pwd,java.lang.String requestXML) { 
        LOG.info("Executing operation getDsr");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#getJzNr(java.lang.String  userid ,)java.lang.String  pwd ,)java.lang.String  requestXML )*
     */
    public java.lang.String getJzNr(java.lang.String userid,java.lang.String pwd,java.lang.String requestXML) { 
        LOG.info("Executing operation getJzNr");
        System.out.println(userid);
        System.out.println(pwd);
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see nju.software.sjy.webservice.cxf.SzftWebService#sendVideo(java.lang.String  uid ,)java.lang.String  pass ,)java.lang.String  xml )*
     */
    public java.lang.String sendVideo(java.lang.String uid,java.lang.String pass,java.lang.String xml) { 
        LOG.info("Executing operation sendVideo");
        System.out.println(uid);
        System.out.println(pass);
        System.out.println(xml);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
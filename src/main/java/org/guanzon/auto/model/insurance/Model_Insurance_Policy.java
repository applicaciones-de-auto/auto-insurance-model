/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guanzon.auto.model.insurance;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.sql.rowset.CachedRowSet;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.base.SQLUtil;
import org.guanzon.appdriver.constant.EditMode;
import org.guanzon.appdriver.constant.TransactionStatus;
import org.guanzon.appdriver.iface.GEntity;
import org.json.simple.JSONObject;

/**
 *
 * @author Arsiela
 */
public class Model_Insurance_Policy implements GEntity{
final String XML = "Model_Insurance_Policy.xml";
    private final String psDefaultDate = "1900-01-01";
    private String psBranchCd;
    private String psExclude = "sTranStat»sOwnrNmxx»cClientTp»sAddressx»sCoOwnrNm»sCSNoxxxx»sFrameNox»sEngineNo»cVhclNewx»sPlateNox»sVhclFDsc»sBrInsNme»sInsurNme"
                                + "»dApplicDt»sApplicNo»sEmployID»sProTrnNo»dPropslDt»sPropslNo»sClientID»sSerialID»sVSPTrnNo»sBrInsIDx»sInsTypID»cIsNewxxx"
//                                + "»nODTCAmtx»nODTCRate»nODTCPrem»nAONCAmtx»nAONCRate"
//                                + "»nAONCRate»nAONCPrem»cAONCPayM»nBdyCAmtx»nBdyCPrem»nPrDCAmtx»nPrDCPrem»nPAcCAmtx»nPacCPrem»nTPLAmtxx»nTPLPremx»nTaxRatex»nTaxAmtxx"
//                                + "»nTotalAmt"
                                + "»sEmpNamex»sBrBankNm»sBankName"; //»
    
    GRider poGRider;                //application driver
    CachedRowSet poEntity;          //rowset
    JSONObject poJSON;              //json container
    int pnEditMode;                 //edit mode

    /**
     * Entity constructor
     *
     * @param foValue - GhostRider Application Driver
     */
    public Model_Insurance_Policy(GRider foValue) {
        if (foValue == null) {
            System.err.println("Application Driver is not set.");
            System.exit(1);
        }

        poGRider = foValue;

        initialize();
    }
    
    private void initialize() {
        try {
            poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());

            poEntity.last();
            poEntity.moveToInsertRow();

            MiscUtil.initRowSet(poEntity);        
            poEntity.updateObject("dTransact", poGRider.getServerDate()); 
            poEntity.updateString("cTranStat", TransactionStatus.STATE_OPEN); 
            poEntity.updateObject("dValidFrm", SQLUtil.toDate(psDefaultDate, SQLUtil.FORMAT_SHORT_DATE));
            poEntity.updateObject("dValidTru", SQLUtil.toDate(psDefaultDate, SQLUtil.FORMAT_SHORT_DATE));
            poEntity.updateDouble("nODTCRate", 0.00);                        
            poEntity.updateDouble("nAONCRate", 0.00);                        
            poEntity.updateDouble("nVATRatex", 0.00);                        
            poEntity.updateDouble("nDocRatex", 0.00);                        
            poEntity.updateDouble("nLGUTaxRt", 0.00);                        
            poEntity.updateBigDecimal("sMVFileNo", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nODTCAmtx", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nAuthFeex", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nODTCPrem", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nAONCAmtx", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nGrossAmt", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nAONCPrem", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("cAONCPayM", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nBdyCAmtx", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nBdyCPrem", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nPrDCAmtx", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nPrDCPrem", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nPAcCAmtx", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nPacCPrem", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nTPLAmtxx", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nTPLPremx", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nDiscAmtx", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nDocAmtxx", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nVATAmtxx", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nNetTotal", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nLGUTaxAm", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nCommissn", new BigDecimal("0.00"));  
            poEntity.updateBigDecimal("nPayAmtxx", new BigDecimal("0.00"));  

            poEntity.insertRow();
            poEntity.moveToCurrentRow();
            poEntity.absolute(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Gets the column index name.
     *
     * @param fnValue - column index number
     * @return column index name
     */
    @Override
    public String getColumn(int fnValue) {
        try {
            return poEntity.getMetaData().getColumnLabel(fnValue);
        } catch (SQLException e) {
        }
        return "";
    }

    /**
     * Gets the column index number.
     *
     * @param fsValue - column index name
     * @return column index number
     */
    @Override
    public int getColumn(String fsValue) {
        try {
            return MiscUtil.getColumnIndex(poEntity, fsValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Gets the total number of column.
     *
     * @return total number of column
     */
    @Override
    public int getColumnCount() {
        try {
            return poEntity.getMetaData().getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int getEditMode() {
        return pnEditMode;
    }

    @Override
    public String getTable() {
        return "insurance_policy";
    }
    
    /**
     * Gets the value of a column index number.
     *
     * @param fnColumn - column index number
     * @return object value
     */
    @Override
    public Object getValue(int fnColumn) {
        try {
            return poEntity.getObject(fnColumn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the value of a column index name.
     *
     * @param fsColumn - column index name
     * @return object value
     */
    @Override
    public Object getValue(String fsColumn) {
        try {
            return poEntity.getObject(MiscUtil.getColumnIndex(poEntity, fsColumn));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sets column value.
     *
     * @param fnColumn - column index number
     * @param foValue - value
     * @return result as success/failed
     */
    @Override
    public JSONObject setValue(int fnColumn, Object foValue) {
        try {
            System.out.println("SET : "+ MiscUtil.getColumnLabel(poEntity, fnColumn) );
            poJSON = MiscUtil.validateColumnValue(System.getProperty("sys.default.path.metadata") + XML, MiscUtil.getColumnLabel(poEntity, fnColumn), foValue);
            if ("error".equals((String) poJSON.get("result"))) {
                System.out.println("ERROR : "+ MiscUtil.getColumnLabel(poEntity, fnColumn) + " : "+  poJSON.get("message"));
                return poJSON;
            }

            poEntity.updateObject(fnColumn, foValue);
            poEntity.updateRow();

            poJSON = new JSONObject();
            poJSON.put("result", "success");
            poJSON.put("value", getValue(fnColumn));
        } catch (SQLException e) {
            e.printStackTrace();
            poJSON.put("result", "error");
            poJSON.put("message", e.getMessage());
        }

        return poJSON;
    }
    
    /**
     * Sets column value.
     *
     * @param fsColumn - column index name
     * @param foValue - value
     * @return result as success/failed
     */
    @Override
    public JSONObject setValue(String fsColumn, Object foValue) {
        poJSON = new JSONObject();

        try {
            return setValue(MiscUtil.getColumnIndex(poEntity, fsColumn), foValue);
        } catch (SQLException e) {
            e.printStackTrace();
            poJSON.put("result", "error");
            poJSON.put("message", e.getMessage());
        }
        return poJSON;
    }

    /**
     * Set the edit mode of the entity to new.
     *
     * @return result as success/failed
     */
    @Override
    public JSONObject newRecord() {
        pnEditMode = EditMode.ADDNEW;

        //replace with the primary key column info
        setTransNo(MiscUtil.getNextCode(getTable(), "sTransNox", true, poGRider.getConnection(), poGRider.getBranchCode()+"POL"));
        setTransactDte(poGRider.getServerDate());
        
        poJSON = new JSONObject();
        poJSON.put("result", "success");
        return poJSON;
    }

    /**
     * Opens a record.
     *
     * @param fsValue - filter values
     * @return result as success/failed
     */
    @Override
    public JSONObject openRecord(String fsValue) {
        poJSON = new JSONObject();

        String lsSQL = getSQL(); //MiscUtil.makeSelect(this, psExclude); //exclude the columns called thru left join
        //replace the condition based on the primary key column of the record
        lsSQL = MiscUtil.addCondition(lsSQL, " a.sTransNox = " + SQLUtil.toSQL(fsValue)
                                                //+ " GROUP BY a.sTransNox "
                                                );

        System.out.println(lsSQL);
        ResultSet loRS = poGRider.executeQuery(lsSQL);

        try {
            if (loRS.next()) {
                for (int lnCtr = 1; lnCtr <= loRS.getMetaData().getColumnCount(); lnCtr++) {
                    setValue(lnCtr, loRS.getObject(lnCtr));
                }

                pnEditMode = EditMode.UPDATE;

                poJSON.put("result", "success");
                poJSON.put("message", "Record loaded successfully.");
            } else {
                poJSON.put("result", "error");
                poJSON.put("message", "No record to load.");
            }
        } catch (SQLException e) {
            poJSON.put("result", "error");
            poJSON.put("message", e.getMessage());
        }

        return poJSON;
    }

    /**
     * Save the entity.
     *
     * @return result as success/failed
     */
    @Override
    public JSONObject saveRecord() {
        poJSON = new JSONObject();

        if (pnEditMode == EditMode.ADDNEW || pnEditMode == EditMode.UPDATE) {
            String lsSQL; 
            if (pnEditMode == EditMode.ADDNEW) {
                //replace with the primary key column info
                setTransNo(MiscUtil.getNextCode(getTable(), "sTransNox", true, poGRider.getConnection(), poGRider.getBranchCode()+"POL"));
                setModifiedBy(poGRider.getUserID());
                setModifiedDte(poGRider.getServerDate());
                
                lsSQL = MiscUtil.makeSQL(this, psExclude);
                
               // lsSQL = "Select * FROM " + getTable() + " a left join (" + makeSQL() + ") b on a.column1 = b.column "
                if (!lsSQL.isEmpty()) {
                    if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), getTargetBranchCd()) > 0) {
                        poJSON.put("result", "success");
                        poJSON.put("message", "Record saved successfully.");
                    } else {
                        poJSON.put("result", "error");
                        poJSON.put("message", poGRider.getErrMsg());
                    }
                } else {
                    poJSON.put("result", "error");
                    poJSON.put("message", "No record to save.");
                }
            } else {
                Model_Insurance_Policy loOldEntity = new Model_Insurance_Policy(poGRider);
                
                //replace with the primary key column info
                JSONObject loJSON = loOldEntity.openRecord(this.getTransNo());

                if ("success".equals((String) loJSON.get("result"))) {
                    setModifiedBy(poGRider.getUserID());
                    setModifiedDte(poGRider.getServerDate());
                    
                    //replace the condition based on the primary key column of the record
                    lsSQL = MiscUtil.makeSQL(this, loOldEntity, "sTransNox = " + SQLUtil.toSQL(this.getTransNo()), psExclude);

                    if (!lsSQL.isEmpty()) {
                        if (poGRider.executeQuery(lsSQL, getTable(), poGRider.getBranchCode(), getTargetBranchCd()) > 0) {
                            poJSON.put("result", "success");
                            poJSON.put("message", "Record saved successfully.");
                        } else {
                            poJSON.put("result", "error");
                            poJSON.put("message", poGRider.getErrMsg());
                        }
                    } else {
                        poJSON.put("result", "success");
                        poJSON.put("message", "No updates has been made.");
                    }
                } else {
                    poJSON.put("result", "error");
                    poJSON.put("message", "Record discrepancy. Unable to save record.");
                }
            }
        } else {
            poJSON.put("result", "error");
            poJSON.put("message", "Invalid update mode. Unable to save record.");
            return poJSON;
        }

        return poJSON;
    }
    
    private String getTargetBranchCd(){
//        if (!poGRider.getBranchCode().equals(getBranchCD())){
//            return getBranchCD();
//        } else {
            return "";
//        }
    }

    /**
     * Prints all the public methods used<br>
     * and prints the column names of this entity.
     */
    @Override
    public void list() {
        Method[] methods = this.getClass().getMethods();

        System.out.println("--------------------------------------------------------------------");
        System.out.println("LIST OF PUBLIC METHODS FOR " + this.getClass().getName() + ":");
        System.out.println("--------------------------------------------------------------------");
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        try {
            int lnRow = poEntity.getMetaData().getColumnCount();

            System.out.println("--------------------------------------------------------------------");
            System.out.println("ENTITY COLUMN INFO");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Total number of columns: " + lnRow);
            System.out.println("--------------------------------------------------------------------");

            for (int lnCtr = 1; lnCtr <= lnRow; lnCtr++) {
                System.out.println("Column index: " + (lnCtr) + " --> Label: " + poEntity.getMetaData().getColumnLabel(lnCtr));
                if (poEntity.getMetaData().getColumnType(lnCtr) == Types.CHAR
                        || poEntity.getMetaData().getColumnType(lnCtr) == Types.VARCHAR) {

                    System.out.println("Column index: " + (lnCtr) + " --> Size: " + poEntity.getMetaData().getColumnDisplaySize(lnCtr));
                }
            }
        } catch (SQLException e) {
        }

    }
    
    /**
     * Gets the SQL statement for this entity.
     *
     * @return SQL Statement
     */
    public String makeSQL() {
        return MiscUtil.makeSQL(this, psExclude);
    }
    
    /**
     * Gets the SQL Select statement for this entity.
     *
     * @return SQL Select Statement
     */
    public String makeSelectSQL() {
        return MiscUtil.makeSelect(this, psExclude);
    }
    
    public String getSQL(){
        return        " SELECT "                                                                                          
                    + "   a.sTransNox "                                                                                   
                    + " , a.dTransact "                                                                                   
                    + " , a.sReferNox "                                                                                   
                    + " , a.dValidFrm "                                                                                   
                    + " , a.dValidTru "                                                                                   
                    + " , a.sPolicyNo "                                                                                   
                    + " , a.sCOCNoxxx "                                                                                   
                    + " , a.sORNoxxxx "                                                                                   
                    + " , a.sMVFileNo "                                                                                   
                    + " , a.nODTCAmtx "                                                                                   
                    + " , a.nODTCRate "                                                                                   
                    + " , a.nODTCPrem "                                                                                   
                    + " , a.nAONCAmtx "                                                                                   
                    + " , a.nAONCRate "                                                                                   
                    + " , a.nAONCPrem "                                                                                   
                    + " , a.cAONCPayM "                                                                                   
                    + " , a.nBdyCAmtx "                                                                                   
                    + " , a.nBdyCPrem "                                                                                   
                    + " , a.nPrDCAmtx "                                                                                   
                    + " , a.nPrDCPrem "                                                                                   
                    + " , a.nPAcCAmtx "                                                                                   
                    + " , a.nPacCPrem "                                                                                   
                    + " , a.nTPLAmtxx "                                                                                   
                    + " , a.nTPLPremx "                                                                                   
                    + " , a.nDocRatex "                                                                                   
                    + " , a.nDocAmtxx "                                                                                   
                    + " , a.nVATRatex "                                                                                   
                    + " , a.nVATAmtxx "                                                                                   
                    + " , a.nLGUTaxRt "                                                                                   
                    + " , a.nLGUTaxAm "                                                                                   
                    + " , a.nAuthFeex "                                                                                   
                    + " , a.nGrossAmt "                                                                                   
                    + " , a.nDiscAmtx "                                                                                   
                    + " , a.nNetTotal "                                                                                   
                    + " , a.nCommissn "                                                                                   
                    + " , a.nPayAmtxx "                                                                                   
                    + " , a.sRemarksx "                                                                                   
                    + " , a.cTranStat "                                                                                   
                    + " , a.sModified "                                                                                   
                    + " , a.dModified "                                                                                   
                    + " , CASE        "                                                                                   
                    + "  WHEN a.cTranStat = "+SQLUtil.toSQL(TransactionStatus.STATE_CLOSED)+" THEN 'APPROVE'      "       
                    + "  WHEN a.cTranStat = "+SQLUtil.toSQL(TransactionStatus.STATE_CANCELLED)+" THEN 'CANCELLED' "       
                    + "  WHEN a.cTranStat = "+SQLUtil.toSQL(TransactionStatus.STATE_OPEN)+" THEN 'ACTIVE'         "       
                    + "  WHEN a.cTranStat = "+SQLUtil.toSQL(TransactionStatus.STATE_POSTED)+" THEN 'POSTED'       "       
                    + "  ELSE 'ACTIVE' "                                                                                  
                    + "    END AS sTranStat "                                                                             
                    /*POLICY APPLICATION */                                                                               
                    + " , b.dTransact AS dApplicDt "                                                                      
                    + " , b.sReferNox AS sApplicNo "                                                                    
                    + " , b.sEmployID "  
                    /*POLICY PROPOSAL*/                                                                                   
                    + " , c.dTransact AS dPropslDt "                                                                      
                    + " , c.sReferNox AS sPropslNo "                                                                      
                    + " , c.sClientID "                                                                                   
                    + " , c.sSerialID "                                                                                   
                    + " , c.sVSPNoxxx AS sVSPTrnNo "                                                                      
                    + " , c.sBrInsIDx "                                                                                   
                    + " , c.sInsTypID "                                                                                   
                    + " , c.cIsNewxxx "                                                                                                 
                    /*CLIENT INFO */                                                                                      
                    + " , d.sCompnyNm AS sOwnrNmxx "                                                                          
                    + " , d.cClientTp "                                                                                   
                    + " , IFNULL(CONCAT( IFNULL(CONCAT(f.sHouseNox,' ') , ''), "                                          
                    + "   IFNULL(CONCAT(f.sAddressx,' ') , ''), "                                                         
                    + "   IFNULL(CONCAT(g.sBrgyName,' '), ''),  "                                                         
                    + "   IFNULL(CONCAT(h.sTownName, ', '),''), "                                                         
                    + "   IFNULL(CONCAT(i.sProvName),'') )	, '') AS sAddressx "                                          
                    + " , m.sCompnyNm AS sCoOwnrNm  "                                                                     
                    + " , j.sCSNoxxxx "                                                                                   
                    + " , j.sFrameNox "                                                                                   
                    + " , j.sEngineNo "                                                                                   
                    + " , j.cVhclNewx "                                                                                   
                    + " , k.sPlateNox "                                                                                   
                    + " , l.sDescript AS sVhclFDsc "                                                                      
                    + " , n.sBrInsNme "                                                                                   
                    + " , o.sInsurNme "                                                                                   
                    + " , p.sCompnyNm AS sEmpNamex "                                                                      
                    + " , q.sBrBankNm "                                                                                   
                    + " , r.sBankName "                                                                                   
                    + " FROM insurance_policy a "                                                                         
                    + " LEFT JOIN insurance_policy_application b  ON b.a.sTransNox = a.sReferNox "                          
                    + " LEFT JOIN insurance_policy_proposal c ON c.sTransNox = b.sReferNox "                              
                    + " LEFT JOIN client_master d ON d.sClientID = c.sClientID "  /*owner*/                               
                    + " LEFT JOIN client_address e ON e.sClientID = c.sClientID AND e.cPrimaryx = '1' "                   
                    + " LEFT JOIN addresses f ON f.sAddrssID = e.sAddrssID "                                              
                    + " LEFT JOIN barangay g ON g.sBrgyIDxx = f.sBrgyIDxx  "                                              
                    + " LEFT JOIN towncity h ON h.sTownIDxx = f.sTownIDxx  "                                              
                    + " LEFT JOIN province i ON i.sProvIDxx = h.sProvIDxx  "                                              
                    + " LEFT JOIN vehicle_serial j ON j.sSerialID = c.sSerialID "                                         
                    + " LEFT JOIN vehicle_serial_registration k ON k.sSerialID = c.sSerialID "                            
                    + " LEFT JOIN vehicle_master l ON l.sVhclIDxx = j.sVhclIDxx "                                         
                    + " LEFT JOIN client_master m ON m.sClientID = j.sCoCltIDx  " /*co-owner*/                            
                    + " LEFT JOIN insurance_company_branches n ON n.sBrInsIDx = c.sBrInsIDx "                             
                    + " LEFT JOIN insurance_company o ON o.sInsurIDx = n.sInsurIDx "                                      
                    + " LEFT JOIN ggc_isysdbf.client_master p ON p.sClientID = b.sEmployID "                              
                    + " LEFT JOIN banks_branches q ON q.sBrBankID = b.sBrBankID "                                         
                    + " LEFT JOIN banks r ON r.sBankIDxx = q.sBankIDxx " ;                                                
                           
    }
    
    private static String xsDateShort(Date fdValue) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(fdValue);
        return date;
    }

    private static String xsDateShort(String fsValue) throws org.json.simple.parser.ParseException, java.text.ParseException {
        SimpleDateFormat fromUser = new SimpleDateFormat("MMMM dd, yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String lsResult = "";
        lsResult = myFormat.format(fromUser.parse(fsValue));
        return lsResult;
    }
    
    /*Convert Date to String*/
    private LocalDate strToDate(String val) {
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(val, date_formatter);
        return localDate;
    }
    
    /**
     * Description: Sets the ID of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setTransNo(String fsValue) {
        return setValue("sTransNox", fsValue);
    }

    /**
     * @return The ID of this record.
     */
    public String getTransNo() {
        return (String) getValue("sTransNox");
    }
    
    
    /**
     * Description: Sets the ID of this record.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setTransactDte(Date fdValue) {
        return setValue("dTransact", fdValue);
    }

    /**
     * @return The ID of this record.
     */
    public Date getTransactDte() {
        return (Date) getValue("dTransact");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setReferNo(String fsValue) {
        return setValue("sReferNox", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getReferNo() {
        return (String) getValue("sReferNox");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setValidFrmDte(Date fdValue) {
        JSONObject loJSON = new JSONObject();
        return setValue("dValidFrm", fdValue);
    }

    /**
     * @return The Value of this record.
     */
    public Date getValidFrmDte() {
        Date date = null;
        if(getValue("dValidFrm") == null || getValue("dValidFrm").equals("")){
            date = SQLUtil.toDate(psDefaultDate, SQLUtil.FORMAT_SHORT_DATE);
        } else {
            date = SQLUtil.toDate(xsDateShort((Date) getValue("dValidFrm")), SQLUtil.FORMAT_SHORT_DATE);
        }
            
        return date;
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setValidTruDte(Date fdValue) {
        JSONObject loJSON = new JSONObject();
        return setValue("dValidTru", fdValue);
    }

    /**
     * @return The Value of this record.
     */
    public Date getValidTruDte() {
        Date date = null;
        if(getValue("dValidTru") == null || getValue("dValidTru").equals("")){
            date = SQLUtil.toDate(psDefaultDate, SQLUtil.FORMAT_SHORT_DATE);
        } else {
            date = SQLUtil.toDate(xsDateShort((Date) getValue("dValidTru")), SQLUtil.FORMAT_SHORT_DATE);
        }
            
        return date;
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setPolicyNo(String fsValue) {
        return setValue("sPolicyNo", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getPolicyNo() {
        return (String) getValue("sPolicyNo");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setCOCNo(String fsValue) {
        return setValue("sCOCNoxxx", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getCOCNo() {
        return (String) getValue("sCOCNoxxx");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setORNo(String fsValue) {
        return setValue("sORNoxxxx", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getORNo() {
        return (String) getValue("sORNoxxxx");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setMVFileNo(String fsValue) {
        return setValue("sMVFileNo", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getMVFileNo() {
        return (String) getValue("sMVFileNo");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setODTCAmt(BigDecimal fdbValue) {
        return setValue("nODTCAmtx", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getODTCAmt() {
        if(getValue("nODTCAmtx") == null || getValue("nODTCAmtx").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nODTCAmtx")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fnValue
     * @return result as success/failed
     */
    public JSONObject setODTCRate(Double fnValue) {
        return setValue("nODTCRate", fnValue);
    }

    /**
     * @return The Value of this record.
     */
    public Double getODTCRate() {
        //return Integer.parseInt(String.valueOf(getValue("nODTCRate")));
        return Double.parseDouble(String.valueOf(getValue("nODTCRate")));
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setODTCPrem(BigDecimal fdbValue) {
        return setValue("nODTCPrem", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getODTCPrem() {
        if(getValue("nODTCPrem") == null || getValue("nODTCPrem").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nODTCPrem")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setAONCAmt(BigDecimal fdbValue) {
        return setValue("nAONCAmtx", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getAONCAmt() {
        if(getValue("nAONCAmtx") == null || getValue("nAONCAmtx").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nAONCAmtx")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fnValue
     * @return result as success/failed
     */
    public JSONObject setAONCRate(Double fnValue) {
        return setValue("nAONCRate", fnValue);
    }

    /**
     * @return The Value of this record.
     */
    public Double getAONCRate() {
        //return Integer.parseInt(String.valueOf(getValue("nAONCRate")));
        return Double.parseDouble(String.valueOf(getValue("nAONCRate")));
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setAONCPrem(BigDecimal fdbValue) {
        return setValue("nAONCPrem", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getAONCPrem() {
        if(getValue("nAONCPrem") == null || getValue("nAONCPrem").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nAONCPrem")));
        }
    }
    
    
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setAONCPayM(String fsValue) {
        return setValue("cAONCPayM", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getAONCPayM() {
        return (String) getValue("cAONCPayM");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setBdyCAmt(BigDecimal fdbValue) {
        return setValue("nBdyCAmtx", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getBdyCAmt() {
        if(getValue("nBdyCAmtx") == null || getValue("nBdyCAmtx").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nBdyCAmtx")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setBdyCPrem(BigDecimal fdbValue) {
        return setValue("nBdyCPrem", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getBdyCPrem() {
        if(getValue("nBdyCPrem") == null || getValue("nBdyCPrem").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nBdyCPrem")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setPrDCAmt(BigDecimal fdbValue) {
        return setValue("nPrDCAmtx", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getPrDCAmt() {
        if(getValue("nPrDCAmtx") == null || getValue("nPrDCAmtx").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nPrDCAmtx")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setPrDCPrem(BigDecimal fdbValue) {
        return setValue("nPrDCPrem", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getPrDCPrem() {
        if(getValue("nPrDCPrem") == null || getValue("nPrDCPrem").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nPrDCPrem")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setPAcCAmt(BigDecimal fdbValue) {
        return setValue("nPAcCAmtx", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getPAcCAmt() {
        if(getValue("nPAcCAmtx") == null || getValue("nPAcCAmtx").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nPAcCAmtx")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setPacCPrem(BigDecimal fdbValue) {
        return setValue("nPacCPrem", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getPacCPrem() {
        if(getValue("nPacCPrem") == null || getValue("nPacCPrem").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nPacCPrem")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setTPLAmt(BigDecimal fdbValue) {
        return setValue("nTPLAmtxx", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getTPLAmt() {
        if(getValue("nTPLAmtxx") == null || getValue("nTPLAmtxx").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nTPLAmtxx")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setTPLPrem(BigDecimal fdbValue) {
        return setValue("nTPLPremx", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getTPLPrem() {
        if(getValue("nTPLPremx") == null || getValue("nTPLPremx").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nTPLPremx")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fnValue
     * @return result as success/failed
     */
    public JSONObject setDocRate(Double fnValue) {
        return setValue("nDocRatex", fnValue);
    }

    /**
     * @return The Value of this record.
     */
    public Double getDocRate() {
        //return Integer.parseInt(String.valueOf(getValue("nTaxRatex")));
        return Double.parseDouble(String.valueOf(getValue("nDocRatex")));
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setDocAmt(BigDecimal fdbValue) {
        return setValue("nDocAmtxx", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getDocAmt() {
        if(getValue("nDocAmtxx") == null || getValue("nDocAmtxx").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nDocAmtxx")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fnValue
     * @return result as success/failed
     */
    public JSONObject setVATRate(Double fnValue) {
        return setValue("nVATRatex", fnValue);
    }

    /**
     * @return The Value of this record.
     */
    public Double getVATRate() {
        //return Integer.parseInt(String.valueOf(getValue("nVATRatex")));
        return Double.parseDouble(String.valueOf(getValue("nVATRatex")));
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setVATAmt(BigDecimal fdbValue) {
        return setValue("nVATAmtxx", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getVATAmt() {
        if(getValue("nVATAmtxx") == null || getValue("nVATAmtxx").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nVATAmtxx")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fnValue
     * @return result as success/failed
     */
    public JSONObject setLGUTaxRt(Double fnValue) {
        return setValue("nLGUTaxRt", fnValue);
    }

    /**
     * @return The Value of this record.
     */
    public Double getLGUTaxRt() {
        //return Integer.parseInt(String.valueOf(getValue("nLGUTaxRt")));
        return Double.parseDouble(String.valueOf(getValue("nLGUTaxRt")));
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setLGUTaxAm(BigDecimal fdbValue) {
        return setValue("nLGUTaxAm", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getLGUTaxAm() {
        if(getValue("nLGUTaxAm") == null || getValue("nLGUTaxAm").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nLGUTaxAm")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setAuthFee(BigDecimal fdbValue) {
        return setValue("nAuthFeex", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getAuthFee() {
        if(getValue("nAuthFeex") == null || getValue("nAuthFeex").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nAuthFeex")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setGrossAmt(BigDecimal fdbValue) {
        return setValue("nGrossAmt", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getGrossAmt() {
        if(getValue("nGrossAmt") == null || getValue("nGrossAmt").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nGrossAmt")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setDiscAmt(BigDecimal fdbValue) {
        return setValue("nDiscAmtx", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getDiscAmt() {
        if(getValue("nDiscAmtx") == null || getValue("nDiscAmtx").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nDiscAmtx")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setNetTotal(BigDecimal fdbValue) {
        return setValue("nNetTotal", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getNetTotal() {
        if(getValue("nNetTotal") == null || getValue("nNetTotal").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nNetTotal")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setPayAmt(BigDecimal fdbValue) {
        return setValue("nPayAmtxx", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getPayAmt() {
        if(getValue("nPayAmtxx") == null || getValue("nPayAmtxx").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nPayAmtxx")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setRemarks(String fsValue) {
        return setValue("sRemarksx", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getRemarks() {
        return (String) getValue("sRemarksx");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setTranStat(String fsValue) {
        return setValue("cTranStat", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getTranStat() {
        return (String) getValue("cTranStat");
    }
    
//    /**
//     * Sets record as active.
//     *
//     * @param fbValue
//     * @return result as success/failed
//     */
//    public JSONObject setActive(boolean fbValue) {
//        return setValue("cTranStat", fbValue ? "1" : "0");
//    }
//
//    /**
//     * @return If record is active.
//     */
//    public boolean isActive() {
//        return ((String) getValue("cTranStat")).equals("1");
//    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setModifiedBy(String fsValue) {
        return setValue("sModified", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getModifiedBy() {
        return (String) getValue("sModified");
    }
    
    /**
     * Sets the date and time the record was modified.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setModifiedDte(Date fdValue) {
        return setValue("dModified", fdValue);
    }

    /**
     * @return The date and time the record was modified.
     */
    public Date getModifiedDte() {
        return (Date) getValue("dModified");
    }
    
//    /**
//     * Description: Sets the Value of this record.
//     *
//     * @param fsValue
//     * @return result as success/failed
//     */
//    public JSONObject setTranStatus(String fsValue) {
//        return setValue("sTranStat", fsValue);
//    }
//
//    /**
//     * @return The Value of this record.
//     */
//    public String getTranStatus() {
//        return (String) getValue("sTranStat");
//    }
    
    /*POLICY APPLICATION*/
    
    /**
     * Description: Sets the ID of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setApplicNo(String fsValue) {
        return setValue("sApplicNo", fsValue);
    }

    /**
     * @return The ID of this record.
     */
    public String getApplicNo() {
        return (String) getValue("sApplicNo");
    }
    
    
    /**
     * Description: Sets the ID of this record.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setApplicDte(Date fdValue) {
        return setValue("dApplicDt", fdValue);
    }

    /**
     * @return The ID of this record.
     */
    public Date getApplicDte() {
        return (Date) getValue("dApplicDt");
    }
    
    /**
     * Description: Sets the ID of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setPropslNo(String fsValue) {
        return setValue("sPropslNo", fsValue);
    }

    /**
     * @return The ID of this record.
     */
    public String getPropslNo() {
        return (String) getValue("sPropslNo");
    }
    
    /**
     * Description: Sets the ID of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setProTrnNo(String fsValue) {
        return setValue("sProTrnNo", fsValue);
    }

    /**
     * @return The ID of this record.
     */
    public String getProTrnNo() {
        return (String) getValue("sProTrnNo");
    }
    
    
    /**
     * Description: Sets the ID of this record.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setPropslDte(Date fdValue) {
        return setValue("dPropslDt", fdValue);
    }

    /**
     * @return The ID of this record.
     */
    public Date getPropslDte() {
        return (Date) getValue("dPropslDt");
    }
    
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setSerialID(String fsValue) {
        return setValue("sSerialID", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getSerialID() {
        return (String) getValue("sSerialID");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setClientID(String fsValue) {
        return setValue("sClientID", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getClientID() {
        return (String) getValue("sClientID");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setVSPTrnNo(String fsValue) {
        return setValue("sVSPTrnNo", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getVSPTrnNo() {
        return (String) getValue("sVSPTrnNo");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setBrInsID(String fsValue) {
        return setValue("sBrInsIDx", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getBrInsID() {
        return (String) getValue("sBrInsIDx");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setInsTypID(String fsValue) {
        return setValue("sInsTypID", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getInsTypID() {
        return (String) getValue("sInsTypID");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setIsNew(String fsValue) {
        return setValue("cIsNewxxx", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getIsNew() {
        return (String) getValue("cIsNewxxx");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setOwnrNm(String fsValue) {
        return setValue("sOwnrNmxx", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getOwnrNm() {
        return (String) getValue("sOwnrNmxx");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setClientTp(String fsValue) {
        return setValue("cClientTp", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getClientTp() {
        return (String) getValue("cClientTp");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setAddress(String fsValue) {
        return setValue("sAddressx", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getAddress() {
        return (String) getValue("sAddressx");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setCoOwnrNm(String fsValue) {
        return setValue("sCoOwnrNm", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getCoOwnrNm() {
        return (String) getValue("sCoOwnrNm");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setVhclFDsc(String fsValue) {
        return setValue("sVhclFDsc", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getVhclFDsc() {
        return (String) getValue("sVhclFDsc");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setCSNo(String fsValue) {
        return setValue("sCSNoxxxx", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getCSNo() {
        return (String) getValue("sCSNoxxxx");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setPlateNo(String fsValue) {
        return setValue("sPlateNox", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getPlateNo() {
        return (String) getValue("sPlateNox");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setFrameNo(String fsValue) {
        return setValue("sFrameNox", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getFrameNo() {
        return (String) getValue("sFrameNox");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setEngineNo(String fsValue) {
        return setValue("sEngineNo", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getEngineNo() {
        return (String) getValue("sEngineNo");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setVhclNew(String fsValue) {
        return setValue("cVhclNewx", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getVhclNew() {
        return (String) getValue("cVhclNewx");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setBrInsNme(String fsValue) {
        return setValue("sBrInsNme", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getBrInsNme() {
        return (String) getValue("sBrInsNme");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setInsurNme(String fsValue) {
        return setValue("sInsurNme", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getInsurNme() {
        return (String) getValue("sInsurNme");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setEmpName(String fsValue) {
        return setValue("sEmpNamex", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getEmpName() {
        return (String) getValue("sEmpNamex");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setEmployID(String fsValue) {
        return setValue("sEmployID", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getEmployID() {
        return (String) getValue("sEmployID");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setBrBankNm(String fsValue) {
        return setValue("sBrBankNm", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getBrBankNm() {
        return (String) getValue("sBrBankNm");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setBankName(String fsValue) {
        return setValue("sBankName", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getBankName() {
        return (String) getValue("sBankName");
    } 
    
}

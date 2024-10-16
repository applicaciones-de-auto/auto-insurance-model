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
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.sql.rowset.CachedRowSet;
import org.guanzon.appdriver.base.CommonUtils;
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
public class Model_Insurance_Policy_Proposal implements GEntity{
final String XML = "Model_Insurance_Policy_Proposal.xml";
    private final String psDefaultDate = "1900-01-01";
    private String psBranchCd;
    private String psExclude = "sTranStat»sOwnrNmxx»cClientTp»sAddressx»sCoOwnrNm»sCSNoxxxx»sFrameNox»sEngineNo»cVhclNewx»sPlateNox»sVhclFDsc»sBrInsNme»sInsurNme»dDelvryDt»nUnitPrce»"
                            + "sBankIDxx»sBankname»sColorDsc»sVhclDesc»sInsAppNo»cPayModex»cVhclSize»sUnitType»sBodyType»dApprovex»sApprover"; //»

    GRider poGRider;                //application driver
    CachedRowSet poEntity;          //rowset
    JSONObject poJSON;              //json container
    int pnEditMode;                 //edit mode

    /**
     * Entity constructor
     *
     * @param foValue - GhostRider Application Driver
     */
    public Model_Insurance_Policy_Proposal(GRider foValue) {
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
            poEntity.updateObject("dDelvryDt", SQLUtil.toDate(psDefaultDate, SQLUtil.FORMAT_SHORT_DATE)); 
            poEntity.updateString("cTranStat", TransactionStatus.STATE_OPEN); 
            poEntity.updateObject("dApproved", SQLUtil.toDate(psDefaultDate, SQLUtil.FORMAT_SHORT_DATE));
            poEntity.updateDouble("nODTCRate", 0.00);
            poEntity.updateDouble("nAONCRate", 0.00);
            poEntity.updateDouble("nTaxRatex", 0.00);
            poEntity.updateBigDecimal("nODTCAmtx", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nODTCPrem", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nAONCAmtx", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nAONCPrem", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nBdyCAmtx", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nBdyCPrem", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nPrDCAmtx", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nPrDCPrem", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nPAcCAmtx", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nPacCPrem", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nTPLAmtxx", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nTPLPremx", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nTaxAmtxx", new BigDecimal("0.00"));
            poEntity.updateBigDecimal("nTotalAmt", new BigDecimal("0.00"));

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
        return "insurance_policy_proposal";
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
//            System.out.println(MiscUtil.getColumnLabel(poEntity, fnColumn));
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
        setTransNo(MiscUtil.getNextCode(getTable(), "sTransNox", true, poGRider.getConnection(), poGRider.getBranchCode()));
        setReferNo(MiscUtil.getNextCode(getTable(), "sReferNox", true, poGRider.getConnection(), poGRider.getBranchCode()));
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
                setTransNo(MiscUtil.getNextCode(getTable(), "sTransNox", true, poGRider.getConnection(), poGRider.getBranchCode()));
                setReferNo(MiscUtil.getNextCode(getTable(), "sReferNox", true, poGRider.getConnection(), poGRider.getBranchCode()));
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
                Model_Insurance_Policy_Proposal loOldEntity = new Model_Insurance_Policy_Proposal(poGRider);
                
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
        return    " SELECT "                                                                           
                + "    a.sTransNox "                                                                   
                + "  , a.dTransact "                                                                   
                + "  , a.sReferNox "                                                                   
                + "  , a.sClientID "                                                                   
                + "  , a.sSerialID "                                                                   
                + "  , a.sVSPNoxxx "                                                                   
                + "  , a.sBrInsIDx "                                                                   
                + "  , a.sInsTypID "                                                                   
                + "  , a.cIsNewxxx "                                                                   
                + "  , a.nODTCAmtx "                                                                   
                + "  , a.nODTCRate "                                                                   
                + "  , a.nODTCPrem "                                                                   
                + "  , a.nAONCAmtx "                                                                   
                + "  , a.nAONCRate "                                                                   
                + "  , a.nAONCPrem "                                                                   
                + "  , a.cAONCPayM "                                                                   
                + "  , a.nBdyCAmtx "                                                                   
                + "  , a.nBdyCPrem "                                                                   
                + "  , a.nPrDCAmtx "                                                                   
                + "  , a.nPrDCPrem "                                                                   
                + "  , a.nPAcCAmtx "                                                                   
                + "  , a.nPAcCPrem "                                                                   
                + "  , a.nTPLAmtxx "                                                                   
                + "  , a.nTPLPremx "                                                                   
                + "  , a.nTaxRatex "                                                                   
                + "  , a.nTaxAmtxx "                                                                   
                + "  , a.nTotalAmt "                                                                   
                + "  , a.sRemarksx "                                                                   
                + "  , a.cTranStat "                                                                   
                + "  , a.sModified "                                                                   
                + "  , a.dModified "                                                                   
                + "  , a.sApproved "                                                                   
                + "  , a.dApproved "  
                + "  , CASE "          
                + " 	WHEN a.cTranStat = "+SQLUtil.toSQL(TransactionStatus.STATE_CLOSED)+" THEN 'APPROVE' "                     
                + " 	WHEN a.cTranStat = "+SQLUtil.toSQL(TransactionStatus.STATE_CANCELLED)+" THEN 'CANCELLED' "                  
                + " 	WHEN a.cTranStat = "+SQLUtil.toSQL(TransactionStatus.STATE_OPEN)+" THEN 'ACTIVE' "                    
                + " 	WHEN a.cTranStat = "+SQLUtil.toSQL(TransactionStatus.STATE_POSTED)+" THEN 'POSTED' "                                      
                + " 	ELSE 'ACTIVE'  "                                                          
                + "    END AS sTranStat "
                + "  , b.sCompnyNm AS sOwnrNmxx "                                                      
                + "  , b.cClientTp "                                                                   
                + "  , TRIM(IFNULL(CONCAT( IFNULL(CONCAT(d.sHouseNox,' ') , ''), "                          
                + "    IFNULL(CONCAT(d.sAddressx,' ') , ''), "                                         
                + "    IFNULL(CONCAT(e.sBrgyName,' '), ''),  "                                         
                + "    IFNULL(CONCAT(f.sTownName, ', '),''), "                                         
                + "    IFNULL(CONCAT(g.sProvName),'') )	, '')) AS sAddressx "                           
                + "  , k.sCompnyNm AS sCoOwnrNm "                                                      
                + "  , h.sCSNoxxxx "                                                                   
                + "  , h.sFrameNox "                                                                   
                + "  , h.sEngineNo "                                                                   
                + "  , h.cVhclNewx "                                                                   
                + "  , i.sPlateNox "                                                                   
                + "  , j.sDescript AS sVhclFDsc "                                                      
                + "  , l.sBrInsNme "                                                                   
                + "  , m.sInsurNme "                                                                
                + "  , DATE(n.dDelvryDt) AS dDelvryDt"                                                                 
                + "  , n.nUnitPrce "                                                               
                + "  , n.cPayModex "                                                           
                + "  , o.sBankIDxx "                                                             
                + "  , o.sBankname "    
                + "  , TRIM(CONCAT_WS(' ',ja.sMakeDesc, jb.sModelDsc, jc.sTypeDesc, j.sTransMsn, j.nYearModl )) AS sVhclDesc "
                + "  , j.cVhclSize "
                + "  , jb.sUnitType "
                + "  , jb.sBodyType "
                + "  , jd.sColorDsc "    
                + "  , p.sTransNox AS sInsAppNo "                                                                                   
                + " , DATE(q.dApproved) AS dApprovex "                                                                           
                + " , r.sCompnyNm AS sApprover "                                                         
                + " FROM insurance_policy_proposal a "                                                 
                + " LEFT JOIN client_master b ON b.sClientID = a.sClientID "  /*owner*/                
                + " LEFT JOIN client_address c ON c.sClientID = a.sClientID AND c.cPrimaryx = '1' "    
                + " LEFT JOIN addresses d ON d.sAddrssID = c.sAddrssID "                               
                + " LEFT JOIN barangay e ON e.sBrgyIDxx = d.sBrgyIDxx  "                               
                + " LEFT JOIN towncity f ON f.sTownIDxx = d.sTownIDxx  "                               
                + " LEFT JOIN province g ON g.sProvIDxx = f.sProvIDxx  "                               
                + " LEFT JOIN vehicle_serial h ON h.sSerialID = a.sSerialID "                          
                + " LEFT JOIN vehicle_serial_registration i ON i.sSerialID = a.sSerialID "             
                + " LEFT JOIN vehicle_master j ON j.sVhclIDxx = h.sVhclIDxx "       
                + " LEFT JOIN vehicle_make ja ON ja.sMakeIDxx = j.sMakeIDxx  "
                + " LEFT JOIN vehicle_model jb ON jb.sModelIDx = j.sModelIDx "
                + " LEFT JOIN vehicle_type jc ON jc.sTypeIDxx = j.sTypeIDxx  "
                + " LEFT JOIN vehicle_color jd ON jd.sColorIDx = j.sColorIDx "                   
                + " LEFT JOIN client_master k ON k.sClientID = h.sCoCltIDx " /*co-owner*/              
                + " LEFT JOIN insurance_company_branches l ON l.sBrInsIDx = a.sBrInsIDx "              
                + " LEFT JOIN insurance_company m ON m.sInsurIDx = l.sInsurIDx "             
                + " LEFT JOIN vsp_master n ON n.sTransNox = a.sVSPNoxxx "             
                + " LEFT JOIN vsp_finance o ON o.sTransNox = a.sVSPNoxxx "            
                + " LEFT JOIN insurance_policy_application p ON p.sReferNox = a.sTransNox AND p.cTranStat <> " + SQLUtil.toSQL(TransactionStatus.STATE_CANCELLED)
                + " LEFT JOIN transaction_status_history q ON q.sSourceNo = a.sTransNox AND q.cTranStat <> "+ SQLUtil.toSQL(TransactionStatus.STATE_CANCELLED)
                + " LEFT JOIN ggc_isysdbf.client_master r ON r.sClientID = q.sApproved " ;                          
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
    public JSONObject setVSPTranNo(String fsValue) {
        return setValue("sVSPNoxxx", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getVSPTranNo() {
        return (String) getValue("sVSPNoxxx");
    }
    
//    /**
//     * Description: Sets the Value of this record.
//     *
//     * @param fsValue
//     * @return result as success/failed
//     */
//    public JSONObject setVSPNo(String fsValue) {
//        return setValue("sVSPNoxxx", fsValue);
//    }
//
//    /**
//     * @return The Value of this record.
//     */
//    public String getVSPNo() {
//        return (String) getValue("sVSPNoxxx");
//    }
    
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
    public JSONObject setPAcCPrem(BigDecimal fdbValue) {
        return setValue("nPAcCPrem", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getPAcCPrem() {
        if(getValue("nPAcCPrem") == null || getValue("nPAcCPrem").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nPAcCPrem")));
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
    public JSONObject setTaxRate(Double fnValue) {
        return setValue("nTaxRatex", fnValue);
    }

    /**
     * @return The Value of this record.
     */
    public Double getTaxRate() {
        //return Integer.parseInt(String.valueOf(getValue("nTaxRatex")));
        return Double.parseDouble(String.valueOf(getValue("nTaxRatex")));
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setTaxAmt(BigDecimal fdbValue) {
        return setValue("nTaxAmtxx", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getTaxAmt() {
        if(getValue("nTaxAmtxx") == null || getValue("nTaxAmtxx").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nTaxAmtxx")));
        }
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setTotalAmt(BigDecimal fdbValue) {
        return setValue("nTotalAmt", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getTotalAmt() {
        if(getValue("nTotalAmt") == null || getValue("nTotalAmt").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nTotalAmt")));
        }
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
    public JSONObject setEntryBy(String fsValue) {
        return setValue("sEntryByx", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getEntryBy() {
        return (String) getValue("sEntryByx");
    }
    
    /**
     * Sets the date and time the record was modified.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setEntryDte(Date fdValue) {
        return setValue("dEntryDte", fdValue);
    }

    /**
     * @return The date and time the record was modified.
     */
    public Date getEntryDte() {
        return (Date) getValue("dEntryDte");
    }
    
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
    
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setApprovedBy(String fsValue) {
        return setValue("sApproved", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getApprovedBy() {
        return (String) getValue("sApproved");
    }
    
    /**
     * Sets the date and time the record was modified.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setApprovedDte(Date fdValue) {
        return setValue("dApproved", fdValue);
    }

    /**
     * @return The date and time the record was modified.
     */
    public Date getApprovedDte() {
        return (Date) getValue("dApproved");
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
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setDelvryDt(Date fdValue) {
        JSONObject loJSON = new JSONObject();
        return setValue("dDelvryDt", fdValue);
    }

    /**
     * @return The Value of this record.
     */
    public Date getDelvryDt() {
        Date date = null;
        if(getValue("dDelvryDt") == null || getValue("dDelvryDt").equals("")){
            date = SQLUtil.toDate(psDefaultDate, SQLUtil.FORMAT_SHORT_DATE);
        } else {
            date = SQLUtil.toDate(xsDateShort((Date) getValue("dDelvryDt")), SQLUtil.FORMAT_SHORT_DATE);
        }
            
        return date;
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdbValue
     * @return result as success/failed
     */
    public JSONObject setUnitPrce(BigDecimal fdbValue) {
        return setValue("nUnitPrce", fdbValue);
    }

    /**
     * @return The Value of this record.
     */
    public BigDecimal getUnitPrce() {
        if(getValue("nUnitPrce") == null || getValue("nUnitPrce").equals("")){
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal(String.valueOf(getValue("nUnitPrce")));
        }
        //return Double.parseDouble(String.valueOf(getValue("nUnitPrce")));
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setBankID(String fsValue) {
        return setValue("sBankIDxx", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getBankID() {
        return (String) getValue("sBankIDxx");
    }
   
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setBankname(String fsValue) {
        return setValue("sBankname", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getBankname() {
        return (String) getValue("sBankname");
    }
   
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setVhclDesc(String fsValue) {
        return setValue("sVhclDesc", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getVhclDesc() {
        return (String) getValue("sVhclDesc");
    }
   
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setVhclSize(String fsValue) {
        return setValue("cVhclSize", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getVhclSize() {
        return (String) getValue("cVhclSize");
    }
   
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setUnitType(String fsValue) {
        return setValue("sUnitType", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getUnitType() {
        return (String) getValue("sUnitType");
    }
   
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setBodyType(String fsValue) {
        return setValue("sBodyType", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getBodyType() {
        return (String) getValue("sBodyType");
    }
   
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setColorDsc(String fsValue) {
        return setValue("sColorDsc", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getColorDsc() {
        return (String) getValue("sColorDsc");
    }
   
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setInsAppNo(String fsValue) {
        return setValue("sInsAppNo", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getInsAppNo() {
        return (String) getValue("sInsAppNo");
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fdValue
     * @return result as success/failed
     */
    public JSONObject setApproveDte(Date fdValue) {
        return setValue("dApprovex", fdValue);
    }

    /**
     * @return The Value of this record.
     */
    public Date getApproveDte() {
        Date date = null;
        if(!getValue("dApprovex").toString().isEmpty()){
            date = CommonUtils.toDate(getValue("dApprovex").toString());
        }
        
        return date;
    }
    
    /**
     * Description: Sets the Value of this record.
     *
     * @param fsValue
     * @return result as success/failed
     */
    public JSONObject setApprover(String fsValue) {
        return setValue("sApprover", fsValue);
    }

    /**
     * @return The Value of this record.
     */
    public String getApprover() {
        return (String) getValue("sApprover");
    }
    
}

package com.sanguine.codegenerator.utility;

import java.util.HashMap;
import java.util.Map;

import static com.sanguine.codegenerator.constants.CodeGeneratorConstants.*;

public class CodeGeneratorUtility {

    public static String getInstanceName(String masterName){

        //System.out.println(masterName);
        String firstLetter = String.valueOf(masterName.charAt(0));
        return masterName.replaceFirst(firstLetter, firstLetter.toLowerCase());
    }

    public static String getFindByCode(String codeFieldName){

        String firstLetter = String.valueOf(codeFieldName.charAt(0));
        codeFieldName = codeFieldName.replaceFirst(firstLetter, firstLetter.toUpperCase());
        return "By"+codeFieldName;
    }

    public static String getFunctionName(String masterName, String prefix, String postfix){

        String functionName = prefix+masterName;
        functionName = postfix!=null ? functionName+postfix : functionName;
        return functionName;
    }

    public static String getGetterMethodName(String fieldName){

        String firstLetter = String.valueOf(fieldName.charAt(0));
        String getterMethod = fieldName.replaceFirst(firstLetter, firstLetter.toUpperCase());
        return "get"+getterMethod+"()";
    }

    public static String getFieldType(String type){

        String columnType = switch (type) {
            case VARCHAR -> "String";
            case CHAR -> "String";
            case DATETIME -> "OffsetDateTime";
            case DATE -> "OffsetDateTime";
            case TIME -> "String";
            case DECIMAL -> "double";
            case DOUBLE -> "double";
            case BIGINT -> "long";
            case INT -> "int";
            case SMALLINT -> "int";
            default -> "";
        };

        return columnType;
    }


    public static Map<String, String> fillTableMap(){

        Map<String, String> tblMap = new HashMap<>();

        tblMap.put("tblaccountmaster", "accountMaster");
        tblMap.put("tbladvancebookingtemp", "advanceBookingTemp");
        tblMap.put("tbladvanceordertypemaster", "advanceOrderTypeMaster");
        tblMap.put("tbladvancereceiptdtl", "advanceReceiptDtl");
        tblMap.put("tbladvancereceipthd", "advanceReceiptHd");
        tblMap.put("tbladvbookbillchardtl", "advBookBillcharDtl");
        tblMap.put("tbladvbookbilldtl", "advBookBillDtl");
        tblMap.put("tbladvbookbillhd", "advBookBillHd");
        tblMap.put("tbladvbookbillimgdtl", "advBookBillingDtl");
        tblMap.put("tbladvbookitemtemp", "advBookItemTemp");
        tblMap.put("tbladvbooktaxdtl", "advBookTaxDtl");
        tblMap.put("tbladvbooktaxtemp", "advBookTaxTemp");
        tblMap.put("tbladvordermodifierdtl", "advOrdermodifierDtl");
        tblMap.put("tblareamaster", "areaMaster");
        tblMap.put("tblareawisedc", "areaWisedc");
        tblMap.put("tblareawisedelboywisecharges", "areaWiseDelboywiseCharges");
        tblMap.put("tblatvreport", "atvReport");
        tblMap.put("tblaudit", "audit");
        tblMap.put("tblbenowsettlementdtl", "benowSettlementDtl");
        tblMap.put("tblbilladvanceamt", "billAdvanceAmt");
        tblMap.put("tblbillcomplementrydtl", "billComplementryDtl");
        tblMap.put("tblbilldiscdtl", "billDiscDtl");
        tblMap.put("tblbilldtl", "billDtl");
        tblMap.put("tblbillhd", "billHd");
        tblMap.put("tblbillmodifierdtl", "billModifierDtl");
        tblMap.put("tblbillpromotiondtl", "billPromotionDtl");
        tblMap.put("tblbillseries", "billSeries");
        tblMap.put("tblbillseriesbilldtl", "billSeriesBillDtl");
        tblMap.put("tblbillsettlementdtl", "billSettlementDtl");
        tblMap.put("tblbilltaxdtl", "billTaxDtl");
        tblMap.put("tblblockedusers", "blockedUsers");
        tblMap.put("tblbookingsourcemaster", "bookingSourceMaster");
        tblMap.put("tblbookingsrcmaster", "bookingSrcMaster");
        tblMap.put("tblbookingstatusmaster", "bookingStatusMaster");
        tblMap.put("tblbuildingmaster", "buildingMaster");
        tblMap.put("tblbuttonsequence", "buttonSequence");
        tblMap.put("tblbuypromotiondtl", "buyPromotionDtl");
        tblMap.put("tblcashdenominations", "cashDenominations");
        tblMap.put("tblcashiermanagement", "cashierManagement");
        tblMap.put("tblcashmanagement", "cashManagement");
        tblMap.put("tblcharactersticsmaster", "charactersticsMaster");
        tblMap.put("tblcharvalue", "charValue");
        tblMap.put("tblcompanymaster", "companyMaster");
        tblMap.put("tblconfig", "config");
        tblMap.put("tblcostcentermaster", "costcenterMaster");
        tblMap.put("tblcounterdtl", "counterDtl");
        tblMap.put("tblcounterhd", "counterHd");
        tblMap.put("tblcreditbillreceipthd", "creditbillReceiptHd");
        tblMap.put("tblcrmpoints", "crmPoints");
        tblMap.put("tblcustareatypemaster", "custareaTypeMaster");
        tblMap.put("tblcustomermaster", "customerMaster");
        tblMap.put("tblcustomertypemaster", "customerTypeMaster");
        tblMap.put("tbldayendprocess", "dayendprocess");
        tblMap.put("tbldayendreports", "dayendReports");
        tblMap.put("tbldcrechargesettlementdtl", "dcRechargeSettlementDtl");
        tblMap.put("tbldebitcardbilldetails", "debitCardBillDetails");
        tblMap.put("tbldebitcardmaster", "debitCardMaster");
        tblMap.put("tbldebitcardrecharge", "debitCardRecharge");
        tblMap.put("tbldebitcardrefundamt", "debitCardRefundQmt");
        tblMap.put("tbldebitcardrevenue", "debitCardRevenue");
        tblMap.put("tbldebitcardsettlementdtl", "debitCardSettlementDtl");
        tblMap.put("tbldebitcardtabletemp", "debitCardTableTemp");
        tblMap.put("tbldebitcardtype", "debitCardType");
        tblMap.put("tbldeliveryboycategorymaster", "deliveryBoyCategoryMaster");
        tblMap.put("tbldeliverypersonmaster", "deliveryPersonMaster");
        tblMap.put("tbldesignationmaster", "designationMaster");
        tblMap.put("tbldisccoreservations", "disccoReservations");
        tblMap.put("tbldiscdtl", "discDtl");
        tblMap.put("tbldischd", "discHd");
        tblMap.put("tblfactorymaster", "factoryMaster");
        tblMap.put("tblfeedbackmaster", "feedbackMaster");
        tblMap.put("tblflagmaster", "flagMaster");
        tblMap.put("tblforms", "forms");
        tblMap.put("tblgiftvoucher", "giftVoucher");
        tblMap.put("tblgiftvoucherissue", "giftVoucherIssue");
        tblMap.put("tblgrouphd", "groupHd");
        tblMap.put("tblhomedeldtl", "homedelDtl");
        tblMap.put("tblhomedelivery", "homedelivery");
        tblMap.put("tblimportexcel", "importExcel");
        tblMap.put("tblinternal", "internal");
        tblMap.put("tblitembarcode", "itemBarcode");
        tblMap.put("tblitemcharctersticslinkupdtl", "itemCharctersticslinkupDtl");
        tblMap.put("tblitemcurrentstk", "itemCurrentstk");
        tblMap.put("tblitemmaster", "itemMaster");
        tblMap.put("tblitemmasterlinkupdtl", "itemMasterlinkupDtl");
        tblMap.put("tblitemmodofier", "itemModofier");
        tblMap.put("tblitemorderingdtl", "itemOrderingDtl");
        tblMap.put("tblitempricingauditdtl", "iTempricingauditDtl");
        tblMap.put("tblitemrtemp", "itemrTemp");
        tblMap.put("tblitemrtemp_bck", "itemrTemp_bck");
        tblMap.put("tblitemtemp", "itemTemp");
        tblMap.put("tblkdsprocess", "kdsProcess");
        tblMap.put("tblkottaxdtl", "kotTaxDtl");
        tblMap.put("tbllaststoreadvbookingbill", "lastStoreAdvBookingBill");
        tblMap.put("tbllinevoid", "lineVoid");
        tblMap.put("tblloyaltypointcustomerdtl", "loyaltyPointCustomerDtl");
        tblMap.put("tblloyaltypointmenuhddtl", "loyaltyPointmenuHdDtl");
        tblMap.put("tblloyaltypointposdtl", "loyaltyPointposDtl");
        tblMap.put("tblloyaltypoints", "loyaltyPoints");
        tblMap.put("tblloyaltypointsubgroupdtl", "loyaltyPointsubgroupDtl");
        tblMap.put("tblmasteroperationstatus", "MasteroPerationstatus");
        tblMap.put("tblmenuhd", "menuHd");
        tblMap.put("tblmenuitempricingdtl", "menuiTempricingDtl");
        tblMap.put("tblmenuitempricinghd", "menuiTempricingHd");
        tblMap.put("tblmodifiergrouphd", "modifierGroupHd");
        tblMap.put("tblmodifiermaster", "modifierMaster");
        tblMap.put("tblnonavailableitems", "nonAvailableItems");
        tblMap.put("tblnonchargablekot", "nonChargableKOT");
        tblMap.put("tblnotificationmaster", "notificationMaster");
        tblMap.put("tbloccasionmaster", "occasionMaster");
        tblMap.put("tblonlinemenuimport", "onlinemenuImport");
        tblMap.put("tblonlineorderdiscdtl", "onlineOrderdiscDtl");
        tblMap.put("tblonlineorderdtl", "onlineOrderDtl");
        tblMap.put("tblonlineorderhd", "onlineOrderHd");
        tblMap.put("tblonlineordermodifierdtl", "onlineOrderModifierDtl");
        tblMap.put("tblonlineordersettlement", "onlineOrderSettlement");
        tblMap.put("tblonlineordertaxdtl", "onlineOrderTaxDtl");
        tblMap.put("tblonlinepaymentconfigdtl", "onlinePaymentConfigDtl");
        tblMap.put("tblonlinepaymentconfighd", "onlinePaymentConfigHd");
        tblMap.put("tblorderanalysis", "orderAnalysis");
        tblMap.put("tblorderdtl", "orderDtl");
        tblMap.put("tblorderhd", "orderHd");
        tblMap.put("tblordermaster", "orderMaster");
        tblMap.put("tblpaymentsetup", "paymentsetup");
        tblMap.put("tblplaceorderadvorderdtl", "placeOrderadvOrderDtl");
        tblMap.put("tblplaceorderdtl", "placeOrderDtl");
        tblMap.put("tblplaceorderhd", "placeOrderHd");
        tblMap.put("tblplayzonepricingdtl", "playzonePricingDtl");
        tblMap.put("tblplayzonepricinghd", "playzonePricingHd");
        tblMap.put("tblpmspostingbilldtl", "pmspostingbillDtl");
        tblMap.put("tblpointsonbill", "pointsOnBill");
        tblMap.put("tblposmaster", "posMaster");
        tblMap.put("tblpossettlementdtl", "posSettlementDtl");
        tblMap.put("tblposwiseitemwiseincentives", "posWiseItemwiseIncentives");
        tblMap.put("tblprintersetup", "printerSetup");
        tblMap.put("tblprintersetupmaster", "printerSetupMaster");
        tblMap.put("tblproductiondtl", "productionDtl");
        tblMap.put("tblproductionhd", "productionHd");
        tblMap.put("tblpromogroupdtl", "promoGroupDtl");
        tblMap.put("tblpromogroupmaster", "promoGroupMaster");
        tblMap.put("tblpromotiondaytimedtl", "promotiondaytimeDtl");
        tblMap.put("tblpromotiondtl", "promotionDtl");
        tblMap.put("tblpromotionmaster", "promotionMaster");
        tblMap.put("tblpropertyimage", "propertyImage");
        tblMap.put("tblpspdtl", "pspDtl");
        tblMap.put("tblpsphd", "pspHd");
        tblMap.put("tblpurchaseorder", "purchaseOrder");
        tblMap.put("tblpurchaseorderdtl", "purchaseOrderDtl");
        tblMap.put("tblpurchaseorderhd", "purchaseOrderHd");
        tblMap.put("tblpurchaseordertaxdtl", "purchaseOrdertaxDtl");
        tblMap.put("tblqadvancereceiptdtl", "qAdvanceReceiptDtl");
        tblMap.put("tblqadvancereceipthd", "qAdvanceReceiptHd");
        tblMap.put("tblqadvbookbillchardtl", "qAdvBookBillcharDtl");
        tblMap.put("tblqadvbookbilldtl", "qAdvBookBillDtl");
        tblMap.put("tblqadvbookbillhd", "qAdvBookbillHd");
        tblMap.put("tblqadvordermodifierdtl", "qAdvOrdermodifierDtl");
        tblMap.put("tblqbillcomplementrydtl", "qBillComplementryDtl");
        tblMap.put("tblqbilldiscdtl", "qBillDiscDtl");
        tblMap.put("tblqbilldtl", "qBillDtl");
        tblMap.put("tblqbillhd", "qBillHd");
        tblMap.put("tblqbillmodifierdtl", "qBillmodifierDtl");
        tblMap.put("tblqbillpromotiondtl", "qBillpromotionDtl");
        tblMap.put("tblqbillsettlementdtl", "qBillsettlementDtl");
        tblMap.put("tblqbilltaxdtl", "qBilltaxDtl");
        tblMap.put("tblqcreditbillreceipthd", "qCreditBillReceiptHd");
        tblMap.put("tblqrmenudineinorders", "qrmenudineinOrders");
        tblMap.put("tblratingmaster", "ratingMaster");
        tblMap.put("tblreasongroupmaster", "reasonGroupMaster");
        tblMap.put("tblreasonmaster", "reasonMaster");
        tblMap.put("tblreasonsubgroupmaster", "reasonSubGroupMaster");
        tblMap.put("tblrecipedtl", "recipeDtl");
        tblMap.put("tblrecipehd", "recipeHd");
        tblMap.put("tblreelorewards", "reeloRewards");
        tblMap.put("tblregisterinoutplayzone", "registerInOutPlayzone");
        tblMap.put("tblregisterterminal", "registerTerminal");
        tblMap.put("tblreorderdtl", "reOrderDtl");
        tblMap.put("tblreorderhd", "reOrderHd");
        tblMap.put("tblreordertime", "reOrdertime");
        tblMap.put("tblreportinggroup", "reportingGroup");
        tblMap.put("tblreservation", "reservation");
        tblMap.put("tblsalessummaryreport", "salesSummaryReport");
        tblMap.put("tblsettelmenthd", "settelmentHd");
        tblMap.put("tblsettlementtax", "settlementTax");
        tblMap.put("tblsetup", "setup");
        tblMap.put("tblshiftmaster", "shiftMaster");
        tblMap.put("tblshortcutkeysetup", "shortcutKeySetup");
        tblMap.put("tblsms", "sms");
        tblMap.put("tblsmssetup", "smsSetup");
        tblMap.put("tblsplitbill", "splitBill");
        tblMap.put("tblsplitbillcomplementrydtl", "splitBillComplementryDtl");
        tblMap.put("tblsplitbilldiscdtl", "splitBillDiscDtl");
        tblMap.put("tblsplitbilldtl", "splitBillDtl");
        tblMap.put("tblsplitbillhd", "splitBillHd");
        tblMap.put("tblsplitbillmodifierdtl", "splitBillModifierDtl");
        tblMap.put("tblsplitbillpromotiondtl", "splitBillPromotionDtl");
        tblMap.put("tblsplitbillsettlementdtl", "splitBillSettlementDtl");
        tblMap.put("tblsplitbilltaxdtl", "splitBilltaxDtl");
        tblMap.put("tblstaffattendance", "staffAttendance");
        tblMap.put("tblstkindtl", "stkinDtl");
        tblMap.put("tblstkinhd", "stkinHd");
        tblMap.put("tblstkintaxdtl", "stkintaxDtl");
        tblMap.put("tblstkoutdtl", "stkoutDtl");
        tblMap.put("tblstkouthd", "stkoutHd");
        tblMap.put("tblstkouttaxdtl", "stkoutTaxDtl");
        tblMap.put("tblstockintemp", "stockinTemp");
        tblMap.put("tblstocktaxdtl", "stocktaxDtl");
        tblMap.put("tblstorelastbill", "storeLastBill");
        tblMap.put("tblstorelastorderno", "storeLastOrderno");
        tblMap.put("tblstructureupdate", "structureUpdate");
        tblMap.put("tblsubgrouphd", "subGroupHd");
        tblMap.put("tblsubgroupmasterlinkupdtl", "subGroupMasterLinkupDtl");
        tblMap.put("tblsubmenuhead", "subMenuHead");
        tblMap.put("tblsuperuserdtl", "superUserDtl");
        tblMap.put("tblsuppliermaster", "supplierMaster");
        tblMap.put("tbltablemaster", "tableMaster");
        tblMap.put("tbltallylinkup", "tallyLinkUp");
        tblMap.put("tbltaxdtl", "taxDtl");
        tblMap.put("tbltaxhd", "taxHd");
        tblMap.put("tbltaxongroup", "taxonGroup");
        tblMap.put("tbltaxposdtl", "taxPOSDtl");
        tblMap.put("tbltaxtemp", "taxTemp");
        tblMap.put("tbltdhcomboitemdtl", "tdhComboItemDtl");
        tblMap.put("tbltdhhd", "tdhHd");
        tblMap.put("tbltempadvorderflash", "tempadvOrderflash");
        tblMap.put("tbltempcompitem", "tempCompItem");
        tblMap.put("tbltemphomedelv", "tempHomeDelv");
        tblMap.put("tbltempitemstk", "tempItemStk");
        tblMap.put("tbltempprintbill", "tempPrintBill");
        tblMap.put("tbltempsalesflash", "tempSalesFlash");
        tblMap.put("tbltempsalesflash1", "tempSalesFlash1");
        tblMap.put("tbltempsalesflashtotals1", "tempSalesFlashTotals1");
        tblMap.put("tbltempvoidkot", "tempVoidKOT");
        tblMap.put("tbltreemast", "treeMast");
        tblMap.put("tbltruncationdtl", "truncationDtl");
        tblMap.put("tbluommaster", "uomMaster");
        tblMap.put("tblupdatekotandbillfromqrmenu", "updateKOTandBillFromqrMenu");
        tblMap.put("tbluserdtl", "userDtl");
        tblMap.put("tbluserforms", "userForms");
        tblMap.put("tbluserhd", "userHd");
        tblMap.put("tbluserpasspolicy", "userPassPolicy");
        tblMap.put("tblviewbilldtl", "viewBillDtl");
        tblMap.put("tblviewbillhd", "viewBillHd");
        tblMap.put("tblvoidadvancereceiptdtl", "voidAdvanceReceiptDtl");
        tblMap.put("tblvoidadvancereceipthd", "voidAdvanceReceiptHd");
        tblMap.put("tblvoidadvbookbillchardtl", "voidAdvBookBillcharDtl");
        tblMap.put("tblvoidadvbookbilldtl", "voidAdvBookBillDtl");
        tblMap.put("tblvoidadvbookbillhd", "voidAdvBookbillHd");
        tblMap.put("tblvoidadvordermodifierdtl", "voidAdvOrdermodifierDtl");
        tblMap.put("tblvoidbill", "voidBill");
        tblMap.put("tblvoidbilldiscdtl", "voidBillDiscDtl");
        tblMap.put("tblvoidbilldtl", "voidBillDtl");
        tblMap.put("tblvoidbillhd", "voidBillHd");
        tblMap.put("tblvoidbillsettlementdtl", "voidBillsettlementDtl");
        tblMap.put("tblvoidbilltaxdtl", "voidBilltaxDtl");
        tblMap.put("tblvoidkot", "voidKOT");
        tblMap.put("tblvoidmodifierdtl", "voidModifierDtl");
        tblMap.put("tblvoidstockdtl", "voidStockDtl");
        tblMap.put("tblvoidstockhd", "voidStockHd");
        tblMap.put("tblvoidstocktaxdtl", "voidStocktaxDtl");
        tblMap.put("tblwaitermaster", "waiterMaster");
        tblMap.put("tblwebbookslinkup", "webBooksLinkup");
        tblMap.put("tblwebclublinkup", "webClubLinkup");
        tblMap.put("tblzonemaster", "zoneMaster");


        return tblMap;
    }

}

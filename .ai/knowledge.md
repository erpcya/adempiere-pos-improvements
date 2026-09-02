# Knowledge Contract — adempiere-pos-improvements

## 1. Identity

| Field | Value |
|---|---|
| Name | adempiere-pos-improvements |
| Repository Type | Library |
| Classification basis | Declaration in `.ai/repository.yml`: `type: Library` |
| Standards | knowledge-contract-v1, repository-classification-v1 |
| Component type | ADempiere Java library with dictionary XML migrations |
| Language and target | Java; `sourceCompatibility = 1.17`, `targetCompatibility = 1.17` |
| Build / runtime | Gradle wrapper 7.3.3; workflows use Temurin JDK 17 |
| Published artifact | Artifact ID `adempiere-pos-improvements`; default group `io.github.adempiere`; publication version from `ADEMPIERE_LIBRARY_VERSION` |
| Version | Tags: `1.0.0`, `1.0.1`, `1.0.2`, `1.0.3`, `1.0.4`, `adempiere-3.9.4-1.0.2`; build fallback `local-1.0.0` |
| License | GPL version 2 (POM license in `build.gradle`) |
| Root package or module | `org.spin.pos` |
| Owner | ERP Consultores y Asociados |
| Fork of upstream | `https://github.com/adempiere/adempiere-pos-improvements` |

## 2. Responsibility

This repository owns reusable POS improvements for ADempiere, delivered as a Library artifact and applied through ADempiere dictionary XML migrations. Its main responsibilities are:

- Creating and maintaining dictionary records for POS configuration: windows, tabs, fields, browsers, views, processes, reports, menus, messages, and setup definitions.
- Providing Java model validators that alter ADempiere document behavior at runtime:
  - `ChangeTax` — changes sales order tax to exempt based on document type.
  - `PaymentAppoval` — sets default payment verification and sends notifications.
  - `ValidateShipment` — validates POS shipments.
- Providing processes:
  - `GenerateRefundFromPOS`
  - `VerifyPayments`
- Providing setup classes that register the model validators:
  - `AddPaymentApproval`
  - `ChangeTaxDeploy`
  - `ValidateShipmentFromPOS`
- Providing a print-ticket extension API:
  - `IPrintTicket`
  - `TicketHandler`
  - `TicketResult`
  - `GenericPrintTicket`

It does not own the ADempiere core, the Vue UI itself, customer-specific customizations, or standalone backend/API services. Its dictionary changes are installed as a library, not as modifications to community base source code.

## 3. Architecture

```text
.github/workflows/
docs/
gradle/wrapper/
src/main/java/org/spin/pos/
  model/validator/
  process/
  setup/
  util/
xml/migration/
build.gradle
gradlew
gradlew.bat
```

Patterns:

- `model.validator` classes implement ADempiere `ModelValidator`.
- `process` concrete classes extend generated abstract process classes (`GenerateRefundFromPOSAbstract`, `VerifyPaymentsAbstract`).
- `setup` classes implement `org.spin.util.ISetupDefinition` and register model validators through ADempiere setup definitions.
- `util` contains shared POS utilities and the print-ticket extension API.
- Dictionary changes are written as ADempiere XML migrations under `xml/migration`.
- `build.gradle` applies `java-library`, `maven-publish`, and `signing`.

### Pre-existing records this repository modifies

The following rows are the records in the evidence's “UPDATES TO PRE-EXISTING RECORDS” set, excluding translations. Where the evidence shows only a partial column view, the row records only that partial view.

| Record | Table | Columns changed | Effect | Migration |
|---|---|---|---|---|
| 449 | AD_Element | EntityType | Pre-existing element updated; evidence shows `EntityType=ECA14` | xml/migration/07020_Add_Report_for_Print_Label_of_Products.xml |
| 55494 | AD_Element | EntityType | Pre-existing element updated; evidence shows `EntityType=ECA14` | xml/migration/07020_Add_Report_for_Print_Label_of_Products.xml |
| 55492 | AD_Element | EntityType | Pre-existing element updated; evidence shows `EntityType=ECA14` | xml/migration/07020_Add_Report_for_Print_Label_of_Products.xml |
| 55490 | AD_Element | EntityType | Pre-existing element updated; evidence shows `EntityType=ECA14` | xml/migration/07020_Add_Report_for_Print_Label_of_Products.xml |
| 617 | AD_Element | EntityType | Pre-existing element updated; evidence shows `EntityType=ECA14` | xml/migration/07020_Add_Report_for_Print_Label_of_Products.xml |
| 163 | AD_Ref_Table | WhereClause, EntityType | Pre-existing reference table updated; evidence shows `WhereClause=` and `EntityType=ECA14` | xml/migration/07020_Add_Report_for_Print_Label_of_Products.xml |
| 50056 | AD_Browse | WhereClause | Pre-existing browse filtered to cash/credit/debit payment types; evidence shows `tt.AD_Reference_ID = 214 AND tt.Value IN ...` | xml/migration/07050_Filter_Cash_Withdrawal_by_Cash_Credit_Card_and_Debit.xml |
| 229 | AD_TreeNodeMM | Node_ID=229, SeqNo=4, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 412 | AD_TreeNodeMM | Node_ID=412, SeqNo=5, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 256 | AD_TreeNodeMM | Node_ID=256, SeqNo=6, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 197 | AD_TreeNodeMM | Node_ID=197, SeqNo=7, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 477 | AD_TreeNodeMM | Node_ID=477, SeqNo=8, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 179 | AD_TreeNodeMM | Node_ID=179, SeqNo=9, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 53626 | AD_TreeNodeMM | Node_ID=53626, SeqNo=10, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 181 | AD_TreeNodeMM | Node_ID=181, SeqNo=11, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 503 | AD_TreeNodeMM | Node_ID=503, SeqNo=14, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 196 | AD_TreeNodeMM | Node_ID=196, SeqNo=15, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 228 | AD_TreeNodeMM | Node_ID=228, SeqNo=16, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 479 | AD_TreeNodeMM | Node_ID=479, SeqNo=17, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 482 | AD_TreeNodeMM | Node_ID=482, SeqNo=18, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 481 | AD_TreeNodeMM | Node_ID=481, SeqNo=19, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 54422 | AD_TreeNodeMM | Node_ID=54422, SeqNo=20, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 411 | AD_TreeNodeMM | Node_ID=411, SeqNo=21, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 426 | AD_TreeNodeMM | Node_ID=426, SeqNo=22, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 53253 | AD_TreeNodeMM | Node_ID=53253, SeqNo=23, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 537 | AD_TreeNodeMM | Node_ID=537, SeqNo=24, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 311 | AD_TreeNodeMM | Node_ID=311, SeqNo=25, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 292 | AD_TreeNodeMM | Node_ID=292, SeqNo=26, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 504 | AD_TreeNodeMM | Node_ID=504, SeqNo=27, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 515 | AD_TreeNodeMM | Node_ID=515, SeqNo=28, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07060_Add_Product_Price_List_Report_Label_Little.xml |
| 492 | AD_TreeNodeMM | Node_ID=492, SeqNo=17, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07080_Add_Check_Price_Form.xml |
| 53269 | AD_TreeNodeMM | Node_ID=53269, SeqNo=18, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07080_Add_Check_Price_Form.xml |
| 491 | AD_TreeNodeMM | Node_ID=491, SeqNo=19, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07080_Add_Check_Price_Form.xml |
| 419 | AD_TreeNodeMM | Node_ID=419, SeqNo=20, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07080_Add_Check_Price_Form.xml |
| 54031 | AD_TreeNodeMM | Node_ID=54031, SeqNo=22, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07080_Add_Check_Price_Form.xml |
| 54033 | AD_TreeNodeMM | Node_ID=54033, SeqNo=23, AD_Tree_ID=10 | Menu tree node reordered | xml/migration/07080_Add_Check_Price_Form.xml |
| 99663 | AD_Field | SeqNo=240 | Field sequence set to 240 | xml/migration/09330_Add_Verification_for_payment.xml |
| 99676 | AD_Field | SeqNo=250 | Field sequence set to 250 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4133 | AD_Field | SeqNo=260 | Field sequence set to 260 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4129 | AD_Field | SeqNo=270 | Field sequence set to 270 | xml/migration/09330_Add_Verification_for_payment.xml |
| 8651 | AD_Field | SeqNo=280 | Field sequence set to 280 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4131 | AD_Field | SeqNo=290 | Field sequence set to 290 | xml/migration/09330_Add_Verification_for_payment.xml |
| 5117 | AD_Field | SeqNo=300 | Field sequence set to 300 | xml/migration/09330_Add_Verification_for_payment.xml |
| 5736 | AD_Field | SeqNo=310 | Field sequence set to 310 | xml/migration/09330_Add_Verification_for_payment.xml |
| 5737 | AD_Field | SeqNo=320 | Field sequence set to 320 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4056 | AD_Field | SeqNo=330 | Field sequence set to 330 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4363 | AD_Field | SeqNo=340 | Field sequence set to 340 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4054 | AD_Field | SeqNo=350 | Field sequence set to 350 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4027 | AD_Field | SeqNo=360 | Field sequence set to 360 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4032 | AD_Field | SeqNo=370 | Field sequence set to 370 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4041 | AD_Field | SeqNo=380 | Field sequence set to 380 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4036 | AD_Field | SeqNo=390 | Field sequence set to 390 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4057 | AD_Field | SeqNo=400 | Field sequence set to 400 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4035 | AD_Field | SeqNo=410 | Field sequence set to 410 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4037 | AD_Field | SeqNo=420 | Field sequence set to 420 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4033 | AD_Field | SeqNo=430 | Field sequence set to 430 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4034 | AD_Field | SeqNo=440 | Field sequence set to 440 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4023 | AD_Field | SeqNo=450 | Field sequence set to 450 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4025 | AD_Field | SeqNo=460 | Field sequence set to 460 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4019 | AD_Field | SeqNo=470 | Field sequence set to 470 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4026 | AD_Field | SeqNo=480 | Field sequence set to 480 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4024 | AD_Field | SeqNo=490 | Field sequence set to 490 | xml/migration/09330_Add_Verification_for_payment.xml |
| 6299 | AD_Field | SeqNo=500 | Field sequence set to 500 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4021 | AD_Field | SeqNo=510 | Field sequence set to 510 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4022 | AD_Field | SeqNo=520 | Field sequence set to 520 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4020 | AD_Field | SeqNo=530 | Field sequence set to 530 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4055 | AD_Field | SeqNo=540 | Field sequence set to 540 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4043 | AD_Field | SeqNo=550 | Field sequence set to 550 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4058 | AD_Field | SeqNo=560 | Field sequence set to 560 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4042 | AD_Field | SeqNo=570 | Field sequence set to 570 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4258 | AD_Field | SeqNo=580 | Field sequence set to 580 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4039 | AD_Field | SeqNo=590 | Field sequence set to 590 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4053 | AD_Field | SeqNo=600 | Field sequence set to 600 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4052 | AD_Field | SeqNo=610 | Field sequence set to 610 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4051 | AD_Field | SeqNo=620 | Field sequence set to 620 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4047 | AD_Field | SeqNo=630 | Field sequence set to 630 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4049 | AD_Field | SeqNo=640 | Field sequence set to 640 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4048 | AD_Field | SeqNo=650 | Field sequence set to 650 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4362 | AD_Field | SeqNo=660 | Field sequence set to 660 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4361 | AD_Field | SeqNo=670 | Field sequence set to 670 | xml/migration/09330_Add_Verification_for_payment.xml |
| 85505 | AD_Field | SeqNo=680 | Field sequence set to 680 | xml/migration/09330_Add_Verification_for_payment.xml |
| 6552 | AD_Field | SeqNo=690 | Field sequence set to 690 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4044 | AD_Field | SeqNo=700 | Field sequence set to 700 | xml/migration/09330_Add_Verification_for_payment.xml |
| 4266 | AD_Field | SeqNo=710 | Field sequence set to 710 | xml/migration/09330_Add_Verification_for_payment.xml |
| 52052 | AD_Field | SeqNo=720 | Field sequence set to 720 | xml/migration/09330_Add_Verification_for_payment.xml |
| 95804 | AD_Field | SeqNo=730 | Field sequence set to 730 | xml/migration/09330_Add_Verification_for_payment.xml |
| 96849 | AD_Field | SeqNo=740 | Field sequence set to 740 | xml/migration/09330_Add_Verification_for_payment.xml |
| 101322 | AD_Field | SeqNo=760 | Field sequence set to 760 | xml/migration/09330_Add_Verification_for_payment.xml |
| 99664 | AD_Field | SeqNo=240 | Field sequence set to 240 | xml/migration/09330_Add_Verification_for_payment.xml |
| 99677 | AD_Field | SeqNo=250 | Field sequence set to 250 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84146 | AD_Field | SeqNo=260 | Field sequence set to 260 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84147 | AD_Field | SeqNo=270 | Field sequence set to 270 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84148 | AD_Field | SeqNo=280 | Field sequence set to 280 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84149 | AD_Field | SeqNo=290 | Field sequence set to 290 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84150 | AD_Field | SeqNo=300 | Field sequence set to 300 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84151 | AD_Field | SeqNo=310 | Field sequence set to 310 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84152 | AD_Field | SeqNo=320 | Field sequence set to 320 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84153 | AD_Field | SeqNo=330 | Field sequence set to 330 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84154 | AD_Field | SeqNo=340 | Field sequence set to 340 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84155 | AD_Field | SeqNo=350 | Field sequence set to 350 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84156 | AD_Field | SeqNo=360 | Field sequence set to 360 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84157 | AD_Field | SeqNo=370 | Field sequence set to 370 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84158 | AD_Field | SeqNo=380 | Field sequence set to 380 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84159 | AD_Field | SeqNo=390 | Field sequence set to 390 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84160 | AD_Field | SeqNo=400 | Field sequence set to 400 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84161 | AD_Field | SeqNo=410 | Field sequence set to 410 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84162 | AD_Field | SeqNo=420 | Field sequence set to 420 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84163 | AD_Field | SeqNo=430 | Field sequence set to 430 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84164 | AD_Field | SeqNo=440 | Field sequence set to 440 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84165 | AD_Field | SeqNo=450 | Field sequence set to 450 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84166 | AD_Field | SeqNo=460 | Field sequence set to 460 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84167 | AD_Field | SeqNo=470 | Field sequence set to 470 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84168 | AD_Field | SeqNo=480 | Field sequence set to 480 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84169 | AD_Field | SeqNo=490 | Field sequence set to 490 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84170 | AD_Field | SeqNo=500 | Field sequence set to 500 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84171 | AD_Field | SeqNo=510 | Field sequence set to 510 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84172 | AD_Field | SeqNo=520 | Field sequence set to 520 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84173 | AD_Field | SeqNo=530 | Field sequence set to 530 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84174 | AD_Field | SeqNo=540 | Field sequence set to 540 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84175 | AD_Field | SeqNo=550 | Field sequence set to 550 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84176 | AD_Field | SeqNo=560 | Field sequence set to 560 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84177 | AD_Field | SeqNo=570 | Field sequence set to 570 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84178 | AD_Field | SeqNo=580 | Field sequence set to 580 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84179 | AD_Field | SeqNo=590 | Field sequence set to 590 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84180 | AD_Field | SeqNo=600 | Field sequence set to 600 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84181 | AD_Field | SeqNo=610 | Field sequence set to 610 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84182 | AD_Field | SeqNo=620 | Field sequence set to 620 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84183 | AD_Field | SeqNo=630 | Field sequence set to 630 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84184 | AD_Field | SeqNo=640 | Field sequence set to 640 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84185 | AD_Field | SeqNo=650 | Field sequence set to 650 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84186 | AD_Field | SeqNo=660 | Field sequence set to 660 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84187 | AD_Field | SeqNo=670 | Field sequence set to 670 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84188 | AD_Field | SeqNo=680 | Field sequence set to 680 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84189 | AD_Field | SeqNo=690 | Field sequence set to 690 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84190 | AD_Field | SeqNo=700 | Field sequence set to 700 | xml/migration/09330_Add_Verification_for_payment.xml |
| 84191 | AD_Field | SeqNo=720 | Field sequence set to 720 | xml/migration/09330_Add_Verification_for_payment.xml |
| 96850 | AD_Field | SeqNo=730 | Field sequence set to 730 | xml/migration/09330_Add_Verification_for_payment.xml |
| 101323 | AD_Field | SeqNo=740 | Field sequence set to 740 | xml/migration/09330_Add_Verification_for_payment.xml |

## 4. Dependencies

| Dependency | Scope | Purpose |
|---|---|---|
| `io.github.adempiere:base:3.9.4` | Build, runtime | ADempiere base model classes and infrastructure used by validators, processes, and utilities |
| `io.github.adempiere:project:3.9.4` | Build, runtime | ADempiere core project/domain classes |
| `lib/*.jar` via `api fileTree(dir: 'lib')` | Build, runtime | Local ADempiere or generated model jars as API dependencies |
| ADempiere model classes such as `I_C_Order`, `I_C_Payment`, `MOrder`, `MPayment`, `MInOut`, `MPOS` | Runtime | Domain objects manipulated by validators and processes |
| `org.spin.queue.notification.DefaultNotifier`, `QueueLoader`, `MADNotificationRecipient`, `IUpdateHandler` | Runtime | Payment approval notification queue integration |
| Gradle wrapper 7.3.3 | Build | Build tooling and wrapper distribution |
| Java 17 | Build, runtime | Compiled and run under Temurin JDK 17 |
| GitHub Actions | CI/CD | Build, publish, release-candidate, and knowledge workflows |
| Maven Central / GitHub Packages | Publication | README points to Maven Central; `build.gradle` default publication URL points to `https://maven.pkg.github.com/erpcya/adempiere-pos-improvements` |

## 5. Consumers

Consumers and the real contracts they use:

- **ADempiere installations / POS Vue UI** — consume the published library artifact and its embedded dictionary XML migrations; they load model validators, processes, and setup definitions. Surfaces that break them: changed Java class names, changed process values, changed dictionary tables/columns/field sequences, changed pre-existing dictionary records.
- **Maven/Gradle/SBT consumers** — consume `io.github.adempiere:adempiere-pos-improvements` according to README. Surface: artifact coordinate and published version.
- **Print-ticket implementors** — consume `IPrintTicket`, `TicketHandler`, and `TicketResult`. Surface: method signatures and class names.
- **ADempiere dictionary synchronization tooling** — consumes `xml/migration/*.xml`. Surface: migration file names, sequence numbers, record IDs, and entity types.
- **Release evaluation / knowledge platform** — consumes this contract and `.ai/repository.yml`.

## 6. Allowed changes

Changes are allowed when they preserve the Library classification and remain reusable across ADempiere installations:

- Add or update dictionary XML migrations under `xml/migration/` for POS windows, tabs, fields, browsers, views, processes, reports, menus, messages, and setup definitions.
- Extend or correct Java validators, processes, setup classes, and utilities under `org.spin.pos`.
- Add new `IPrintTicket` implementations and adjust `TicketHandler`/`TicketResult` as long as the existing public API is preserved or migrated.
- Update `build.gradle` and CI workflows for Java 17, Gradle wrapper, publication target, and the platform’s knowledge/release workflows.
- Change pre-existing dictionary records only through explicit migrations with a documented reason and confirmed record IDs.
- Update this contract when a change alters any fact described here.

## 7. Prohibited changes

- Do not change the declared `Repository Type` in `.ai/repository.yml` away from `Library` without an explicit, separate organizational decision and a versioned standard update if required.
- Do not modify generated ADempiere abstract process classes by hand; changes must follow the generator or be explicitly recorded as a generated update.
- Do not remove or rename public classes or methods in `org.spin.pos` without a migration for dictionary references and notification consumers.
- Do not publish the fork’s artifact as if it were the upstream Maven Central artifact without an explicit group/version decision; the two sources share an artifact ID and README coordinate.
- Do not add secrets, credentials, tokens, or sensitive configuration values to tracked files.
- Do not treat identifier ranges as ownership; record IDs are not evidence of who owns a dictionary record.
- Do not describe functionality owned by the ADempiere core or Vue UI as if it belongs to this repository.

## 8. Architectural rules

1. Dictionary changes must be represented as XML migrations under `xml/migration/`.
2. Java implementation classes must remain under `org.spin.pos`.
3. Model validators must register themselves through `ISetupDefinition` implementations or ADempiere setup definitions, preserving that runtime registration path.
4. Pre-existing dictionary record modifications must be explicit, traceable, and reviewed against the section 3 table.
5. Artifact publication must remain reproducible from `build.gradle` and GitHub Actions, with the publication target and version driven by the release environment.
6. Every contract update must preserve verified findings, especially section 11 unknowns, until evidence resolves them.

## 9. Risks

### Mandatory checks

| Check | Finding | Impact | Precaution |
|---|---|---|---|
| Identifiers outside the allowed allocation range | No repository-specific allocation range is declared in the evidence. Observed created `Record_ID` values are 5- and 6-digit; no 7+ digit identifiers appear. | Cannot confirm allocation validity. | Obtain and record the authorized dictionary identifier allocation for this repository. |
| Build output or IDE metadata under version control | Yes: `.classpath`, `.project`, and `.settings/` are tracked. | IDE state in version control causes dirty checkouts and machine-specific diffs. | Remove `.classpath`, `.project`, and `.settings/` from tracking and add them to `.gitignore`. |
| Secrets in the tree or recoverable from history | None found. Build and CI files reference credential-bearing environment variables; the evidence collector masked the right-hand sides. No actual secret value is visible, and no commit removing one is shown. | None observed. | Keep credentials in GitHub Actions secrets or environment variables; never print or commit actual values. |
| Absent verification mechanism | No test source files or test suite are present in the tracked file list. Build runs compile only. | Behavior changes may compile while remaining unverified in operation. | Use the `erp-ai:candidate` manual verification workflow and add automated tests for behavior changes. |
| Pre-existing records modified (cross-reference section 3) | Yes: 138 pre-existing dictionary records across `AD_Element`, `AD_Ref_Table`, `AD_Browse`, `AD_TreeNodeMM`, and `AD_Field` are updated. | Updates to records installations already have can alter core windows, menus, and field ordering unexpectedly. | Review the section 3 table for every release; require explicit migration review before merging dictionary updates. |

### Additional risks

| Risk | Impact | Precaution |
|---|---|---|
| Entity type inconsistency | `.ai/repository.yml` and `build.gradle` manifest declare entity type `D`, but most migrations and setup classes use `ECA14`; some migration records use `ECA02` or `ECA23`. Dictionary synchronization and release evaluation can misattribute records or treat them as another module’s. | Owner must decide the intended entity type and align marker/build/migrations, or document the deliberate cross-module usages. |
| Upstream artifact coordinate collision | README points consumers to Maven Central under `io.github.adempiere:adempiere-pos-improvements`, while the fork’s build default publication URL is GitHub Packages for `erpcya/adempiere-pos-improvements`. Consumers can resolve the wrong artifact. | Publish with an explicit, documented group/URL per release and avoid ambiguous README/artifact instructions. |
| `lib/*.jar` dependency directory not tracked | `build.gradle` declares `api fileTree(dir: 'lib')`, but no `lib/` directory appears in tracked files. Local builds may depend on untracked or externally supplied jars. | Verify how `lib/` is populated; document it or remove the dependency if not needed. |
| Pre-existing record updates are numerous | Large numbers of `AD_Field` and `AD_TreeNodeMM` sequence changes can silently reorder core windows and menus. | Treat sequence changes as breaking for user experience and include them in release notes. |

## 10. Current state

At the time of this update, the repository is a Java 17/Gradle library for ADempiere POS improvements. It contains 93 dictionary migration files under `xml/migration`, Java model validators and processes under `src/main/java/org/spin/pos`, and a print-ticket extension API. It is a fork of upstream `https://github.com/adempiere/adempiere-pos-improvements`, with the default branch `erpya`. Tags show released versions `1.0.0` through `1.0.4` and `adempiere-3.9.4-1.0.2`.

The repository modifies at least 138 pre-existing dictionary records, primarily `AD_Field` sequences in the payment verification windows and `AD_TreeNodeMM` menu ordering. No automated tests are present; verification is compilation plus manual release-candidate checks. The `.ai/repository.yml` declares entity type `D`, while migration and setup code predominantly uses entity type `ECA14`, with a smaller number of records using `ECA02` or `ECA23`.

## 11. UNKNOWN

- The intended and authoritative dictionary entity type: `D` as declared in `.ai/repository.yml` and `build.gradle`, or `ECA14` as used by most migrations and setup classes. Verify with the repository owner.
- The actual published groupId for fork releases when `ADEMPIERE_LIBRARY_GROUP` is or is not set. The evidence does not show the exact value; verify in CI configuration or release documentation.
- The full column-level change set for some pre-existing record updates. Section 3 records only the columns visible in the evidence summary.
- Whether `lib/*.jar` is required and populated at build time, or whether the `fileTree` dependency is effectively empty. Verify in local checkout or CI build logs.
- The authorized dictionary `Record_ID` allocation range for this repository. Verify with the owner or dictionary allocation documentation.
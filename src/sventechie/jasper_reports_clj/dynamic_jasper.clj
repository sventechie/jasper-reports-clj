(ns sventechie.jasper-reports-clj.dynamic-jasper
  (:require [clj-time.core :as t])
  (:import
    [java.util Map]
    [ar.com.fdvs.dj.core DynamicJasperHelper]
    [ar.com.fdvs.dj.domain DJCalculation]
    [ar.com.fdvs.dj.domain DJValueFormatter]
    [ar.com.fdvs.dj.core.layout ClassicLayoutManager]
    ;[net.sf.jasperreports.view JasperDesignViewer]
    ;[net.sf.jasperreports.view JasperViewer]
    [ar.com.fdvs.dj.domain DynamicReport]
    [ar.com.fdvs.dj.domain.builders FastReportBuilder])
  (:gen-class))

(def xml-encoding "UTF-8")

(def typemap
  {:boolean "java.lang.Boolean"
   :byte "java.lang.Byte"
   :short "java.lang.Short"
   :int "java.lang.Integer"
   :long "java.lang.Long"
   :float "java.lang.Float"
   :double "java.lang.Double"
   :string "java.lang.String"
   :date "java.util.Date"
   :object "java.lang.Object"})

;DynamicJasperHelper.generateJRXML(dr, new ClassicLayoutManager() , parameters, "UTF-8", destinationFilePath);
(defn build-jrxml
  "Build JRxml file from DynamicJasper report object"
  [report-object output-file parameter-map]
  (let [layout-manager (new ClassicLayoutManager)
        parameters (java.util.HashMap. parameter-map)]
    (DynamicJasperHelper/generateJRXML
      report-object layout-manager parameters xml-encoding output-file)))

(defn build-report
  "Create Jasper report"
  [file-name parameter-map]
  (let [report-builder (new FastReportBuilder)
        report-object (.. report-builder
    ;; column, title, data_type_name, width
    (addColumn "State", "state", (:string typemap), 30)
    (addColumn "Branch", "branch", (:string typemap), 30)
    (addColumn "Product Line", "productLine", (:string typemap), 50)
    (addColumn "Item", "item", (:string typemap), 50)
    (addColumn "Item Code", "id", (:string typemap), 30)
    (addColumn "Quantity", "quantity", (:long typemap), 60, true)
    (addColumn "Amount", "amount", (:float typemap), 70, true)
    (addGroups 2)
    (setTitle "November \"2014\" sales report")
    (setSubtitle (str "This report was generated at " (t/date-time 2014 11 01)))
    (setPrintBackgroundOnOddRows true)
    (setUseFullPageWidth true)
    (build))]

    (build-jrxml report-object file-name parameter-map)))


;(defn fastReportTest-main []
;  (let [testR (helloWorld.report.FastReportTest.)]
;    (. testR testReport)
;    (exportToJRXML)
;    (. JasperViewer (viewReport [(. testR jp)]))
;    (. JasperDesignViewer (viewReportDesign [(. testR jr)]))))

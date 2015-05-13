(ns jasper-reports-clj.dynamic-jasper-orig
  (:import
    [java.util Date]
    [java.util Map]
    [ar.com.fdvs.dj.domain DJCalculation]
    [ar.com.fdvs.dj.domain DJValueFormatter]
    [net.sf.jasperreports.view JasperDesignViewer]
    [net.sf.jasperreports.view JasperViewer]
    [ar.com.fdvs.dj.domain DynamicReport]
    [ar.com.fdvs.dj.domain.builders FastReportBuilder])
  (:gen-class :name helloWorld.report.FastReportTest :extends BaseDjReportTest
    :prefix fastReportTest-
    :main true
    :aot [helloWorld.report.FastReportTest def]))


(defn fastReportTest-build-report []
  ((def  drb (new FastReportBuilder)))

  (.. (drb) (addColumn ["State", "state", (.getName String),30])
    (addColumn ["Branch", "branch", (.getName String),30])
    (addColumn ["Product Line", "productLine", (.getName String),50])
    (addColumn ["Item", "item", (.getName String),50])
    (addColumn ["Item Code", "id", (.getName String),30])
    (addColumn ["Quantity", "quantity", (.getName String),60,true])
    (addColumn ["Amount", "amount", (.getName String),70,true])
    (addGroups [2])
    (setTitle ["November \"2006\" sales report"])
    (setSubtitle [(+ "This report was generated at " (new Date()))])
    (setPrintBackgroundOnOddRows [true])
    (setUseFullPageWidth [true])
    (build [])
  )


  (def dr (. drb build))

 )



(defn fastReportTest-main []

  (let [testR (helloWorld.report.FastReportTest.)]
    (. testR testReport)
    (exportToJRXML)
    (. JasperViewer (viewReport [(. testR jp)]))
    (. JasperDesignViewer (viewReportDesign [(. testR jr)]))))

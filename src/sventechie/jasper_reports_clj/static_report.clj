(ns sventechie.jasper-reports-clj.static-report
  (:import [net.sf.jasperreports.engine JasperFillManager JasperExportManager JasperCompileManager JRDataSource]
           ;[net.sf.jasperreports.engine.export JRXlsExporter JRCsvExporter JRTextExporter JRRtfExporter JRGraphics2DExporter]
           ;[net.sf.jasperreports.engine.export.ooxml JRXlsxExporter JRPptxExporter JRDocxExporter]
           )
  (:require [clojure.java.io :as io]
            [clojure.java.jdbc :as jdbc]))

(def format-extension
  {:pdf ".pdf"
   :html ".html"
   :xls ".xls"
   :xlsx ".xlsx"
   :csv ".csv"
   :rtf ".rtf"
   :pptx ".pptx"
   :text ".txt"
   :txt ".txt"
   :png ".png"
   :jpg ".jpg"})

;(def db-spec {:subprotocol "mysql" :subname "//127.0.0.1:3306/report_test?zeroDateTimeBehavior=convertToNull" :user "clojure_test" :password "clojure_test"})

(def report-dir "templates/reports/")

(def mock-data {:id 42 :name "Some Company" :project_number 99876})

(defn map->java
  "Convert Clojure hash-map to Java HashMap object"
  [data-map]
  (let [hashmap-reducer (fn [m [k v]] (doto m (.put (name k) v)))]
    (reduce hashmap-reducer (java.util.HashMap.) data-map)))

;; see http://jasperreports.sourceforge.net/api/net/sf/jasperreports/engine/JasperExportManager.html
;; for stream output, etc.

(defn compile-report
  [file-basename]
  "Compile JRxml to JasperPrint file"
  (JasperCompileManager/compileReportToFile
   (str report-dir file-basename ".jrxml")
   (str report-dir file-basename ".jasper")))

(defn fill-report
  [file-basename parameter-map database-spec]
  "Fill JasperPrint file with data"
  (JasperFillManager/fillReportToFile
   (str report-dir file-basename ".jasper")
   (str report-dir file-basename ".out")
   (map->java parameter-map)
   (jdbc/get-connection database-spec)))

;(defn export-to-file
;  [file-basename output-format & embed-images?]
;  "Export filled JasperPrint 'out' file to PDF"
;  (let [file-base (str report-dir file-basename)]
;    (let [input-file (str file-base ".out")]
;      (cond
;       (= :pdf output-format)
;         (JasperExportManager/exportReportToPdfFile input-file
;           (str file-base ".pdf"))
;       (= :xml output-format)
;         (JasperExportManager/exportReportToXmlFile input-file
;           (str file-base ".xml"))
;       (= :csv output-format)
;         (JasperExportManager/exportReportToCsvFile input-file
;           (str file-base ".csv"))
;       (= :html output-format)
;         (JasperExportManager/exportReportToHtmlFile input-file
;           (str file-base ".html"))
;       (= :text output-format)
;         (JasperExportManager/exportReportToTextFile input-file
;           (str file-base ".html"))
;       ))))

(defn export-to-pdf
  [in-basename out-basename]
  "Export filled JasperPrint 'out' file to PDF"
  (JasperExportManager/exportReportToPdfFile
   (str report-dir in-basename ".out")
   (str report-dir out-basename ".pdf")))

(defn export-to-html
  [in-basename out-basename]
  "Export filled JasperPrint out file to HTML"
  (JasperExportManager/exportReportToHtmlFile
   (str report-dir in-basename ".out")
   (str report-dir out-basename ".html")))

;(defn export-to-csv
;  [file-basename]
;  "Export filled JasperPrint out file to HTML"
;  (JasperExportManager/exportReportToCsvFile
;   (str report-dir file-basename ".out")
;   (str report-dir file-basename ".csv")))

(defn export-to-xml
  [in-basename out-basename embed-images?]
  "Export filled JasperPrint out file to XML"
  (JasperExportManager/exportReportToXmlFile
   (str report-dir in-basename ".out")
   (str report-dir out-basename ".xml")
   embed-images?))


(defn jasper->pdf
  "Output PDF from Jasper file"
  [in-basename out-basename parameter-map db-spec]
  (compile-report in-basename)
  (fill-report in-basename parameter-map db-spec)
  (export-to-pdf in-basename out-basename))

(defn fill->pdf
  "Output PDF from JRxml file"
  [in-basename out-basename parameter-map db-spec]
  (fill-report in-basename parameter-map db-spec)
  (export-to-pdf in-basename out-basename))

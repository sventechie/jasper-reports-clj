(defproject sventechie/jasper-reports-clj "0.1.0-SNAPSHOT"
  :description "Clojure library for making Jasper Reports"
  :url "http://github.com/sventechie/jasper-reports-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [cheshire "5.4.0"]

                 [net.sf.jasperreports/jasperreports "6.0.3"]
                 [ar.com.fdvs/DynamicJasper "5.0.3"]

                 [org.clojure/java.jdbc "0.3.6"]
                 [mysql/mysql-connector-java "5.1.35"]]
  :plugins [[lein-ancient "0.5.5"]]
  :repositories {"fdvsolution.public" "http://nexus.fdvs.com.ar/content/groups/public/"})

(defproject sventechie/jasper-reports-clj "0.1.0-SNAPSHOT"
  :description "Clojure library for making Jasper Reports"
  :url "http://github.com/sventechie/jasper-reports-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [cheshire "5.5.0"]
                 [clj-time "0.11.0"]

                 [net.sf.jasperreports/jasperreports "6.1.0"]
                 [ar.com.fdvs/DynamicJasper "5.0.3"]

                 [org.clojure/java.jdbc "0.4.2"]
                 ;[mysql/mysql-connector-java "5.1.36"]
                 [hsqldb/hsqldb "1.8.0.10"]]
  :plugins [[lein-ancient "0.5.5"]]
  :repositories {"fdvsolution.public" "http://nexus.fdvs.com.ar/content/groups/public/"})

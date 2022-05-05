(defproject invoices "0.0.1-SNAPSHOT"
  :description "Making my invoices faster"
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :profiles {:dev
             {:dependencies [[midje "1.7.0"]
                             [clojurewerkz/money "1.10.0"]]}
             :midje {}})

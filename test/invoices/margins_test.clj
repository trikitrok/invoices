(ns invoices.margins-test
  (:require [midje.sweet :refer :all]
            [invoices.margins :refer :all]
            [invoices.money :refer :all]))

(facts
  "about `margins`"
  (fact
    "are computed from the total billing"
    (margin 5378.00) => {:percentage 9.17M
                         :total-margin (euros 493.16M)}
    (margin 7040.00) => {:percentage 12.00M
                         :total-margin (euros 844.8M)})
  (fact
    "are at least of 5%"
    (margin 0) => {:percentage 5M
                   :total-margin (euros 0)})

  (fact
    "are at most of 12%"
    (margin 10000000) => {:percentage 12M
                          :total-margin (euros 1200000.00)}))

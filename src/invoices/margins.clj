(ns invoices.margins
  (:require
    [invoices.money :refer :all])
  (:import
    (java.math RoundingMode)))

(def ^:private max-invoice 12.00)
(def ^:private min-invoice 5.0)
(def ^:private billing-for-max-invoice 7040.00)

(defn- round-to-2 [n]
  (.setScale (bigdec n) 2 RoundingMode/HALF_UP))

(defn- compute-percentage [billing]
  (min max-invoice
       (max min-invoice
            (* (/ billing billing-for-max-invoice) max-invoice))))

(defn margin [billing]
  (let [percentage (round-to-2 (compute-percentage billing))
        margin (* billing (/ percentage 100.00))]
    {:percentage percentage
     :total-margin  (euros (round-to-2 margin))}))

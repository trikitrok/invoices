(ns invoices.invoices
  (:require
    [invoices.margins :refer :all]
    [invoices.money :refer :all]
    [invoices.invoice :refer :all]
    [invoices.billings :refer :all]
    [invoices.notes :refer :all]))

(defn- compute-margin [invoices-data]
  (let [total-billing (compute-total-billing invoices-data)]
    (margin total-billing)))

(defn compute-invoice [{:keys [billings]} {:keys [percentage]}]
  (invoice (map #(euros %) billings) percentage))

(defn- compute-invoices [invoices-data margin]
  (map #(merge % (compute-invoice % margin)) invoices-data))

(defn- normalize [invoices-data]
  (map
    (fn [invoice]
      (let [billings (vec (flatten [(:billings invoice)]))]
        (assoc invoice :billings billings)))
    invoices-data))

(defn invoices [invoices-data]
  (let
    [invoices-data (normalize invoices-data)
     margin (compute-margin invoices-data)]
    {:invoices (compute-invoices invoices-data margin)
     :note (compose-note invoices-data margin)}))

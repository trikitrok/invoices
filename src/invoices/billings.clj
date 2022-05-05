(ns invoices.billings)

(defn client-billing [billings]
  (apply + billings))

(defn compute-total-billing [invoices-data]
  (apply + (map #(client-billing (:billings %)) invoices-data)))

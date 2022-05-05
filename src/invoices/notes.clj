(ns invoices.notes
  (:require
    [invoices.billings :refer :all]))

(defn- client-note [invoice-data]
  (str
    (client-billing (:billings invoice-data))
    " (" (:client invoice-data) ")"))

(defn- format-clients-billings-note [invoices-data]
  (str (reduce
         (fn [acc invoice-data]
           (str acc (client-note invoice-data) " + "))
         ""
         (butlast invoices-data))
       (client-note (last invoices-data))))

(defn compose-note [invoices-data {:keys [percentage]}]
  (str "Nota para quien la revise:\n\nEl margen aplicado es del "
       percentage
       "% que viene de introducir el total del mes\n"
       (format-clients-billings-note invoices-data)
       " = "
       (compute-total-billing invoices-data)
       "\nen la hoja de cálculo"))
(ns invoices.invoices-test
  (:require
    [midje.sweet :refer :all]
    [invoices.money :refer :all]
    [invoices.invoices :refer :all]))


(facts
  "about computing monthly invoices"

  (invoices
    [{:billings 5000.00M
      :client "Lifull"}
     {:billings [78.00M 300M]
      :client "Audiense"}]) => {:invoices
                                [{:client "Lifull"
                                  :billings [(euros 5000.00M)]
                                  :percentage 9.17M
                                  :subtotal (euros 5000.00M)
                                  :margin (euros 458.50M)
                                  :base (euros 4541.50M)
                                  :iva (euros 953.72M)
                                  :base-plus_iva (euros 5495.22M)
                                  :irpf (euros 681.23M)
                                  :to-pay (euros 4813.99)}
                                 {:client "Audiense"
                                  :billings [(euros 78.00M) (euros 300M)]
                                  :percentage 9.17M
                                  :subtotal (euros 378.00M)
                                  :margin (euros 34.66M)
                                  :base (euros 343.34M)
                                  :iva (euros 72.10M)
                                  :base-plus_iva (euros 415.44M)
                                  :irpf (euros 51.50M)
                                  :to-pay (euros 363.94M)}]
                                  :note "Nota para quien la revise:\n\nEl margen aplicado es del 9.17% que viene de introducir el total del mes\n5000.00 (Lifull) + 378.00 (Audiense) = 5378.00\nen la hoja de cálculo"})

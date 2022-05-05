(ns invoices.invoice-test
  (:require
    [midje.sweet :refer :all]
    [invoices.money :refer :all]
    [invoices.invoice :refer :all]))

(facts
  "about computing invoices"
  (fact
    "with one billing"
    (invoice
      [(euros 1000.00M)]
      10.00M) => {:billings [(euros 1000.00M)]
                  :percentage 10.00M
                  :subtotal (euros 1000.00M)
                  :margin (euros 100.00M)
                  :base (euros 900.00M)
                  :iva (euros 189.00M)
                  :base-plus_iva (euros 1089.00M)
                  :irpf (euros 135.00M)
                  :to-pay (euros 954.00M)}

    (invoice
      [(euros 4260.00M)]
      7.26M) => {:billings [(euros 4260.00M)]
                 :percentage 7.26M
                 :subtotal (euros 4260.00M)
                 :margin (euros 309.28M)
                 :base (euros 3950.72M)
                 :iva (euros 829.65M)
                 :base-plus_iva (euros 4780.37M)
                 :irpf (euros 592.61)
                 :to-pay (euros 4187.76)})

  (fact
    "with several billings"
    (invoice
      [(euros 4060.00M)
       (euros 200.00M)]
      7.26M) => {:billings [(euros 4060.00M) (euros 200.00M)]
                 :percentage 7.26M
                 :subtotal (euros 4260.00M)
                 :margin (euros 309.28M)
                 :base (euros 3950.72M)
                 :iva (euros 829.65M)
                 :base-plus_iva (euros 4780.37M)
                 :irpf (euros 592.61)
                 :to-pay (euros 4187.76)}))
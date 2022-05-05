(ns invoices.invoice
  (:require
    [invoices.money :refer :all]))

(def ^:private iva-percent 0.21M)
(def ^:private irpf-percent 0.15M)

(defn invoice [billings margin-percentage]
  (let
    [billing (total billings)
     margin (multiply billing (/ margin-percentage 100.00M))
     base (minus billing margin)
     iva (multiply base iva-percent)
     base-plus-iva (plus base iva)
     irpf (multiply base irpf-percent)
     to-pay (minus base-plus-iva irpf)]
    {:billings billings
     :percentage margin-percentage
     :subtotal billing
     :margin margin
     :base base
     :iva iva
     :base-plus_iva base-plus-iva
     :irpf irpf
     :to-pay to-pay}))
(ns addon.browser
  [:require
   [clojure.string]
   [clojure.core]])


(def temp-content
"
...
"
  )



;; (defn scan-files-supported-url [supported-url]
;;   (let [doc (-> js/document .-documentelement .-innerhtml)]
;;     doc))
;;
(defn scan-url-formats [supported-url]
  (filter :urls (map #(assoc % :urls (re-seq (:regex %) temp-content))
                     (:formats supported-url))))

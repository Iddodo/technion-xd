(ns addon.technion
  [:require
   [clojure.string]])

(def gr++
  {:title "GR++"
   :supported-urls
   [{:title :generic
     :regex #"^https://grades\.cs\.technion.ac\.il/grades\.cgi\?.+?(?=\.html)\.html$"
     :formats [{:regex #"https\://grades\.cs\.technion\.ac\.il/grades\.cgi\?.+?\.pdf"
                :ext "pdf"}]}]})

(def supported-sites [gr++])

(defn parse-url-site [url site]
  (let [supported-url (first (filter #(re-seq (:regex %) url)
                                          (:supported-urls site)))]
    (when supported-url {:site site :supported-url supported-url})))

(defn parse-url [url]
  (some (partial parse-url-site url) supported-sites))

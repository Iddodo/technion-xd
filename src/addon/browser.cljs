(ns addon.browser
  [:require
   [clojure.string]
   [addon.technion :as technion]
   [addon.db :as db]])

(defn parse-technion-url-site [url site]
  (let [supported-url (some true? (filter #(re-seq (get % :regex) url)
                                          (get site :supported-urls)))]
    (when supported-url {:website site :url-type supported-url})))

(defn parse-url [url]
  (some true? (map (partial parse-technion-url-site url) technion/supported-sites)))

(defn scan-files [{:keys [url-type]}]
  (let [document-str (js/document.documentElement.outerHTML)]
    (-> (get url-type :formats)
        (partial map #(hash-map :ext (get % :ext)
                        :matches (re-seq (get % :regex) document-str)))
        (partial filter #(true? (get % :matches))))))


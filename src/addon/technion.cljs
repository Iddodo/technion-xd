(ns addon.technion
  [:require
   [clojure.string]])

(defonce gr++
  {:supported-urls
   [{:name :generic-page
     :regex #"^https://grades\.cs\.technion.ac\.il/grades\.cgi\?.+?(?=\.html)\.html$"
     :formats [{:regex #"^<A HREF=\"(https://grades.cs.technion.ac.il/grades.cgi\?.+?(?=\.pdf)\.pdf)\">$"
                :ext "pdf"}]}]})

(defonce supported-sites [gr++])

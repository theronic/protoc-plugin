;;;----------------------------------------------------------------------------------
;;; Generated by protoc-gen-clojure.  DO NOT EDIT
;;;
;;; GRPC com.example.addressbook.Greeter Service Implementation
;;;----------------------------------------------------------------------------------
(ns com.example.addressbook.Greeter
  (:require [com.example.addressbook :refer :all]
            [clojure.core.async :as async]
            [protojure.grpc.client.utils :refer [send-unary-params invoke-unary]]
            [promesa.core :as p]
            [protojure.grpc.client.api :as grpc]
))

;-----------------------------------------------------------------------------
; GRPC Client Implementation
;-----------------------------------------------------------------------------

(defn call-Hello
  [client params]
  (let [input (async/chan 1)
        output (async/chan 1)
        desc {:service "com.example.addressbook.Greeter"
              :method  "Hello"
              :input   {:f com.example.addressbook/new-Person :ch input}
              :output  {:f com.example.addressbook/pb->HelloResponse :ch output}}]
    (-> (send-unary-params input params)
        (p/then (fn [_] (invoke-unary client desc output))))))


;;----------------------------------------------------------------------------------
;;----------------------------------------------------------------------------------
;; GRPC Server Implementation
;;----------------------------------------------------------------------------------
;;----------------------------------------------------------------------------------

;-----------------------------------------------------------------------------
; GRPC Greeter
;-----------------------------------------------------------------------------
(defprotocol Service
  (Hello [this param]))

(defn- Hello-dispatch
  [ctx request]
  (Hello ctx request))

(def ^:const rpc-metadata
  [{:pkg "com.example.addressbook" :service "Greeter" :method "Hello" :method-fn Hello-dispatch :server-streaming false :client-streaming false :input pb->Person :output new-HelloResponse}])


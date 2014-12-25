# Camelot embedded with Hazelcast exclusion example

Example showing the way how to use the Camelot framework embedded without Hazelcast
dependency. Aggregators will work in the local memory only.

Use the following steps to start sample

```bash
git clone git@github.com:camelot-framework/camelot-embedded-example-whz.git

cd camelot-embedded-example-whz

mvn clean compile jetty:run
```

Then you can find webapp at [http://localhost:8080](http://localhost:8080)

Sending events and receiving the states:
```bash
curl 'http://localhost:8080/test/post?msg=Hello,World1&uuid=key1'
curl 'http://localhost:8080/test/post?msg=Hello,World2&uuid=key2'
curl 'http://localhost:8080/test/post?msg=Hello,World3&uuid=key3'
curl http://localhost:8080/test/get
```

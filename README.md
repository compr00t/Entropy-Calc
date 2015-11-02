This repo contains a set of tools I developed or extended.
Use at your own risk!

## entorpyCalc
A tool for dividing the input in X parts and calculating the entorpy of each. 
This can help analysing a file encrypted by a ransomware regarding its quality.

Call the prepared JAR passing the path of the file (path/to/file) and the number of parts (numb_of_parts) the file should be divided into. Optionally you can pass a max value for the entropy to display:

```java
java -jar entorpyCalc.jar /path/to/file numb_of_parts [max_entorpy_val]

# Geocoding
1. Created 24 test cases to verify API with URL: https://maps.googleapis.com/maps/api/geocode/json
2. Some test cases required understanding "Actual results" It reason why they are commented
3. I created some tests to check some field in the body which we can use as a base for other verifications
4. CombinationOfNullInAddressTest generates data for future tests but anyway checked that the request is successful.
5. Descript
6. I launched the Jenkins machine on AWS EC2. This machine isn't powerful. Its reason for unstable behavior sometimes
Jenkins URL: http://18.223.188.0:8080/
Login: Marqeta Password: Marqeta
7. In Jenkins, I used Maven Project. I didn't implement Pipeline because need to know CI logic for this tests. Anyway we can use trigger for CI purpose

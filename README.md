Geocoding
Assignment for MNTN

Created 21 test cases to verify API with URL: https://maps.googleapis.com/maps/api/geocode/json . Created API Key in https://console.developers.google.com/
Geocoding is the process of converting addresses (like a street address) into geographic coordinates (like latitude and longitude), which you can use to place markers on a map, or position the map.

Reverse geocoding is the process of converting geographic coordinates into a human-readable address.

I created some tests to check some field in the body which we can use as a base for other verifications
CombinationOfNullInAddressTest generates data for future tests but anyway checked that the request is successful.
I've used RestAssured library to build API connetcion.
I've used Yaml to store data for tests
# image-detector

## Build and run

./gradlew clean build

./gradlew bootRun

## Configuration

Imagga keys are hard coded and can be re-set in application.properties.

## Testing with cURL:

### POST

image upload: convert image to base64 and upload it in json payload

base64 -i horse.jpg -o horse_base64.txt

curl -X POST http://localhost:8080/images -H "Content-Type: application/json" -d "{\"data\": \"$(cat horse_base64.txt)\",\"dataType\": \"jpg\",\"objectDetection\": true}"


image url:

curl -X POST http://localhost:8080/images -H "Content-Type: application/json" -d "{\"url\": \"<url goes here>\",\"objectDetection\": true}"

### GET

curl -X GET http://localhost:8080/images

curl -X GET http://localhost:8080/images?objects="horse"


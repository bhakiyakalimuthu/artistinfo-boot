# Viaplay Task Assignment
    Rest service to get Music artist information

# Feature supported

- Throttling(Rate limiting) 
- Fault tolerance/Retry with Circuit breaker
- Caching 


# Pre requisites 
- Mac(10.14)
- Maven - 3.3.9
- Spring Boot - 2.1.2.RELEASE
- Java - 11.0.2 2019-01-15 LTS(OpenJDK build by Oracle)

# Build & Run
> Application can be build and started two ways.
> Make sure to cd to project folder.

- Maven build & Run Default

```
mvn clean package && java -jar executable/target/artistinfo-boot.jar

```

- Docker build & Run Default
```
docker build -t artistinfo-boot .

docker run -p 8080:8080 -ti artistinfo-boot
```

- Test api
```
http://localhost:8080/service-api/v1/info/{mbid}

Ex: http://localhost:8080/service-api/v1/info/f27ec8db-af05-4f36-916e-3d57f91ecf5e
```

- Response
```
{
    "mbid": "f27ec8db-af05-4f36-916e-3d57f91ecf5e",
    "description": "American singer, dancer, entertainer, songwriter, producer and recording artist.\r\n\r\nBorn: 29 August 1958 in Gary, Indiana, USA. \r\nDied: 25 June 2009 in Los Angeles, California, USA (aged 50). \r\n\r\nKnown affectionately as the \"King Of Pop\", Jackson was a singer, dancer, musician, music producer, writer, entertainer, singer-songwriter, choreographer, record producer, recording artist, poet, arranger, businessman, philanthropist, actor, voice artist, and comedian. \r\n \r\nJackson began his career as the youngest member of [a=The Jackson 5] and started his solo recording career in 1971. Brother of recording artists [a=Jackie Jackson], [a=Janet Jackson], [a=Jermaine Jackson], [a=La Toya Jackson], [a=Marlon Jackson], [a=Randy Jackson], [a=Rebbie Jackson] & [a=Tito Jackson], as well as uncle of [a=3T].\r\n\r\nInducted into Rock And Roll Hall of Fame in 2001 (as performer).\r\n\r\nOn June 25, 2009, Michael Jackson died of acute propofol and benzodiazepine intoxication at his home on North Carolwood Drive in the Holmby Hills neighborhood of Los Angeles, CA. His personal physician, Conrad Murray, said he had found Jackson in his room, not breathing and with a barely detectable pulse, and that he administered CPR on Jackson to no avail. After a call was placed to 9-1-1 at 12:21 p.m., Jackson was treated by paramedics at the scene and was later pronounced dead at the Ronald Reagan UCLA Medical Center.",
    "albums": [
        {
            "id": "f32fab67-77dd-3937-addc-9062e28e4c37",
            "title": "Thriller",
            "images": [
                "http://coverartarchive.org/release/959272f9-97ae-4179-aebe-950eef64ed93/2700087954.jpg",
                "http://coverartarchive.org/release/959272f9-97ae-4179-aebe-950eef64ed93/2700090127.jpg",
                "http://coverartarchive.org/release/959272f9-97ae-4179-aebe-950eef64ed93/2700091394.jpg",
                "http://coverartarchive.org/release/959272f9-97ae-4179-aebe-950eef64ed93/2700092734.jpg"
            ]
        },
        {
            "id": "ffc3f8b5-7b22-40ed-8867-0cad52b6b2ae",
            "title": "18 Greatest Hits",
            "images": [
                "http://coverartarchive.org/release/75276adf-7504-4bba-8630-631ef020c31b/1871938266.jpg",
                "http://coverartarchive.org/release/75276adf-7504-4bba-8630-631ef020c31b/16649358642.jpg",
                "http://coverartarchive.org/release/75276adf-7504-4bba-8630-631ef020c31b/16649359300.jpg"
            ]
        },
        {
            "id": "e6696f23-a356-4cff-a096-fdf2a1e1a358",
            "title": "Great Songs and Performances That Inspired the Motown 25th Anniversary Television Special",
            "images": [
                "http://coverartarchive.org/release/d77fe3d8-8a4b-4849-87d3-dabfb9f75e44/1619672416.jpg"
            ]
        }
 )
        
```
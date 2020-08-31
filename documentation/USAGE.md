# Usage

### Setup
- Download Docker
- Download Project
  - git clone git@github.com:CCThomas/my-book.git
- For Development
  - MySql for DB (Or just use the Docker Service)
  - Maven & Java is needed for backend
  - npm is needed for frontend


### Run
- Spin up Database, Backend, and Frontend
  - `docker-compose up`

### Development
- Spin up Database, needed for Backend
  - `docker-compose up -d db`
- Run Backend
  - Navigate to `./backend`
  - `mvn spring-boot:run`
- Run Frontend
  - Navigate to `./frontend`
  - `npm start`

### Clean Up
- Nuke Docker
  - Create an executable
    - `chmod +x docker-nuke.sh`
  - Run docker nuke
    - `./docker-nuke.sh`

### Troubleshooting
TODO
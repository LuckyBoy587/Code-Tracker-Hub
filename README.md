# Code Tracker Hub

A comprehensive full-stack web application that helps developers track their coding progress across multiple competitive programming platforms, including LeetCode and GeeksForGeeks (GFG). The application provides an intuitive dashboard to monitor problem-solving statistics, daily challenges, and user profiles.

## 🌟 Features

- **Multi-Platform Integration**: Track your progress on LeetCode and GeeksForGeeks
- **Problem Statistics**: View detailed statistics about problems solved (Easy, Medium, Hard)
- **Daily Challenges**: Access Problem of the Day (POD) from both platforms
- **User Profiles**: Manage and track multiple coding platform usernames
- **Google OAuth Authentication**: Secure login using Google authentication
- **Real-time Data**: Fetch live data from coding platforms using web scraping and GraphQL APIs
- **Responsive UI**: Modern, responsive interface built with React and Material-UI
- **Visual Analytics**: Charts and progress bars to visualize your coding journey

## 🛠️ Tech Stack

### Frontend
- **React 19.1.0** - UI library
- **Vite** - Build tool and development server
- **React Router DOM** - Client-side routing
- **Material-UI (MUI)** - Component library
- **Axios** - HTTP client
- **Recharts** - Data visualization
- **Framer Motion** - Animation library
- **Styled Components** - CSS-in-JS styling
- **React Icons** - Icon library
- **Google OAuth** - Authentication

### Backend
- **Spring Boot 3.4.5** - Java framework
- **Spring Data JPA** - Database ORM
- **MySQL** - Database
- **Selenium 4.32.0** - Web scraping
- **jsoup 1.20.1** - HTML parsing
- **Lombok** - Boilerplate code reduction
- **Maven** - Dependency management

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Node.js** (v18 or higher)
- **npm** (v9 or higher)
- **Java JDK 17** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **Git**

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/LuckyBoy587/Code-Tracker-Hub.git
cd Code-Tracker-Hub
```

### 2. Database Setup

Create a MySQL database:

```sql
CREATE DATABASE codetrackerhub;
```

### 3. Backend Setup

Navigate to the backend directory:

```bash
cd "Coding-Platform-API SpringBoot"
```

Update the database configuration in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/codetrackerhub
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

Install dependencies and run the application:

```bash
# Using Maven wrapper (Unix/Linux/Mac)
./mvnw clean install
./mvnw spring-boot:run

# Using Maven wrapper (Windows)
mvnw.cmd clean install
mvnw.cmd spring-boot:run

# Or using Maven directly
mvn clean install
mvn spring-boot:run
```

The backend server will start on `http://localhost:8080`

### 4. Frontend Setup

Navigate to the frontend directory:

```bash
cd "Coding-Platform-UI ReactJS"
```

Install dependencies:

```bash
npm install
```

Configure Google OAuth:
- Create a Google OAuth 2.0 Client ID in [Google Cloud Console](https://console.cloud.google.com/)
- Update the OAuth configuration in your authentication provider

Run the development server:

```bash
npm run dev
```

The frontend application will start on `http://localhost:5173`

## 🔧 Configuration

### Backend Configuration

The `application.properties` file contains the following configurations:

```properties
spring.application.name=Coding-Platform-API
spring.datasource.url=jdbc:mysql://localhost:3306/codetrackerhub
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Frontend Configuration

Update the API base URL in your frontend code if deploying to production:
- Currently configured for `http://localhost:8080`

## 📚 API Endpoints

### LeetCode Endpoints

- **GET** `/leetcode/pod` - Get LeetCode Problem of the Day
- **GET** `/leetcode/user_profile/{username}` - Get user profile data
- **GET** `/leetcode/problems-solved/{username}` - Get problems solved statistics

### GeeksForGeeks Endpoints

- **GET** `/gfg/pod` - Get GFG Problem of the Day
- **GET** `/gfg/user_profile/{username}` - Get user profile data
- **GET** `/gfg/problem-solved/{username}` - Get problems solved statistics

### User Profile Endpoints

- **POST** `/user/update` - Update user profile with coding platform usernames

## 📁 Project Structure

```
Code-Tracker-Hub/
├── Coding-Platform-API SpringBoot/     # Backend Spring Boot application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/first/codingplatformapi/
│   │   │   │       ├── configuration/     # CORS and Selenium config
│   │   │   │       ├── controller/        # REST controllers
│   │   │   │       ├── entity/            # JPA entities
│   │   │   │       ├── repository/        # Data repositories
│   │   │   │       ├── service/           # Business logic
│   │   │   │       └── utilities/         # Helper utilities
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── graphql/              # GraphQL queries
│   │   └── test/                         # Unit tests
│   ├── pom.xml                           # Maven dependencies
│   └── mvnw                              # Maven wrapper
│
├── Coding-Platform-UI ReactJS/          # Frontend React application
│   ├── src/
│   │   ├── Authentication/              # Auth components
│   │   ├── gfg/                         # GFG components
│   │   ├── leetcode/                    # LeetCode components
│   │   ├── navbar/                      # Navigation bar
│   │   ├── profile/                     # User profile
│   │   ├── App.jsx                      # Main app component
│   │   └── main.jsx                     # Entry point
│   ├── public/                          # Static assets
│   ├── package.json                     # NPM dependencies
│   ├── vite.config.js                   # Vite configuration
│   └── index.html                       # HTML template
│
└── README.md                            # This file
```

## 🎮 Usage

1. **Login**: Use Google OAuth to sign in to the application
2. **Set Up Profile**: Add your LeetCode and GeeksForGeeks usernames in the profile section
3. **View Statistics**: Navigate to LeetCode or GFG pages to see your problem-solving statistics
4. **Track Progress**: Monitor your daily challenges and overall progress through visual charts
5. **Update Profile**: Edit your usernames anytime from the profile page

## 🧪 Running Tests

### Backend Tests

```bash
cd "Coding-Platform-API SpringBoot"
./mvnw test
```

### Frontend Tests

```bash
cd "Coding-Platform-UI ReactJS"
npm run test
```

## 🔨 Building for Production

### Backend

```bash
cd "Coding-Platform-API SpringBoot"
./mvnw clean package
```

The JAR file will be created in the `target/` directory.

### Frontend

```bash
cd "Coding-Platform-UI ReactJS"
npm run build
```

The production build will be created in the `dist/` directory.

## 🌐 Deployment

### Backend Deployment
- Deploy the Spring Boot JAR to any Java-compatible hosting service (AWS, Heroku, etc.)
- Ensure MySQL database is accessible
- Update `application.properties` with production database credentials

### Frontend Deployment
- Deploy the `dist/` folder to static hosting services (Vercel, Netlify, etc.)
- Update API endpoints to point to production backend URL

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines

- Follow existing code style and conventions
- Write meaningful commit messages
- Add tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting PR

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

**LuckyBoy587**
- GitHub: [@LuckyBoy587](https://github.com/LuckyBoy587)

## 🙏 Acknowledgments

- LeetCode for providing the GraphQL API
- GeeksForGeeks for the platform data
- All contributors who help improve this project

## 📞 Support

If you encounter any issues or have questions:
- Open an issue on GitHub
- Check existing issues for solutions
- Contact the maintainer

## 🗺️ Roadmap

Future enhancements planned:
- [ ] Add support for more coding platforms (Codeforces, HackerRank, etc.)
- [ ] Implement detailed analytics and insights
- [ ] Add social features to compare progress with friends
- [ ] Mobile application
- [ ] Email notifications for daily challenges
- [ ] Export progress reports

---

**Happy Coding! 🚀**

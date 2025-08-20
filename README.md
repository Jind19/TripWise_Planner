# 🗺️ TripPlanner Microservice (Part of TripWise)

TripPlanner is the **Itinerary Service** in the **TripWise** ecosystem.  
It helps users plan trips, manage packing lists, and personalize templates.

---

## 🌍 TripWise Ecosystem

- **Gateway → TripHub 🛂** → Central entry point for all requests
- **Auth Service → TripPass ✈️** → Authentication & access control (JWT / OAuth2)
- **User Profile Service → TripProfile 🧳** → User identity & preferences
- **Itinerary Service → TripPlanner 🗺️** → Trip organization and packing lists
- **Journal Service → TripJournal 📖** → Capture activities, notes, and memories
- **Weather Service → TripWeather ☀️** → Current weather and forecast from external API
- **Media Service → TripMediaVault 📸** → Photos, avatars, attachments

---

## 📌 Responsibilities (TripPlanner)
- Manage trips (destination, dates, number of people, budget).
- Create and customize packing lists.
- Offer predefined templates (Beach, Jungle, Business).
- Allow users to edit templates without changing the originals.

---


## 🚀 Tech Stack
- Java 17+
- Spring Boot 3.x
- Spring Web (REST API)
- Spring Data JPA (Hibernate ORM)
- PostgreSQL (production DB)
- Spring Validation (DTO validation)
- Springdoc OpenAPI (Swagger UI for API docs)
- Lombok (reduce boilerplate code)
- Spring Boot Actuator (monitoring/health checks)

---

## 🔐 Authentication
- Handled by **TripPass (Auth Service)** via OAuth2 / JWT.
- TripPlanner trusts user identity from TripHub (Gateway) and TripProfile (User Service).
- Each trip and packing list is scoped to the authenticated user.

---

## 📚 API Overview

## 🌐 Service Endpoints
https://tripwise:9093/tripplanner

### Example REST Endpoints (TripPlanner)
- `POST /tripplanner/trips` → Create a new trip
- `GET /tripplanner/trips/{tripId}` → Get trip details
- `GET /tripplanner/packing-templates` → List packing templates
- `POST /tripplanner/packing-templates/{templateId}/clone?tripId=...` → Clone template for user trip
- `GET /tripplanner/user-packing-lists/{tripId}` → Get user's packing list for a trip
- `PUT /tripplanner/user-packing-lists/{listId}/items/{itemId}` → Update packing item status

---

## 📊 Example Use Case

Imagine Alice wants to plan a **family trip to Spain**:

1. She creates a **Trip**:
    - Destination: Spain, Barcelona
    - Dates: 10–20 Aug
    - People: 4
    - Budget: 2000 €

2. She selects the **Beach Packing Template**.
    - Template has items like *Sunscreen, Swimwear, Towels*.

3. The system clones this template into **Alice’s Packing List**.
    - She edits it by adding *Baby Food* and removing *Laptop*.
    - Each edited list is her own copy → original template remains unchanged.

4. She marks items as **Packed/Not Packed** while preparing.

This microservice tracks all of the above.

---

## 👩‍💻 Author
Prachi Jindal


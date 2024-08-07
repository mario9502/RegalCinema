Regal Cinemas - SoftUni Spring Project

User Roles - There are two user roles (USER and ADMIN) as the first registered automatically becomes the one and only Admin.

Admin Panel - This is where a programs, movies, and cinemas can be added. And only the Admin user is able to make some changes at all. 
To add a program the movies info section must be completed exactly as shown in the example place holder. Only movies which are already saved in the DB can be added (can be checked in the Movies section).

Cinemas Section - There can be seen all the cinemas and some info about them.

Program Section - Contains the movie schedule for each cinema by date. There is a cron job set for 00:02 every day which deletes the outdated program.

Movie Section - All the movies that are currently on screen with information (when clicked on the title).

REST API - The movies logic is separeted in a another project and consumed by Rest Client. Link: https://github.com/mario9502/RegalCinemas-Movies

When starting the main project initializing process with cinema will start and with the REST API project there will be one with movies. Users and Programs must be created manually.

The database used is MySQL.

I apologize in advance for the mess :)

------------------------------------------------------------------------
This is the project README file. 

This project was developed at Randstad in the training, "RePrograma a tua Carreira", under supervision of Challenge.IT, Lda.

Randstad selected a group of 10 people to send back to classroom, from several different areas and whose level of education was at least a degree. 
These people were not selected to teach, they where selected to learn. This is a pilot program that will remake this professionals's skills.
The temporary employment agency is performing this original kind of training in association with TI Coriant. 

------------------------------------------------------------------------

PROJECT TITLE: ShelfProject - TeamWork03 - Etapa1

PURPOSE OF PROJECT: This application was developed to manage a shelf.
This project is the implementation of an HTTP server component and its clients.
These should support the existing commands in step 3 of the previous team work.
The first phase of this project consists of altering the existing application,
to allow HTTP requests to the server previously developed.
These requests must be made through the rich client (user interface) and the response to these should be returned in JSON;

VERSION/DATE:  ********

HOW TO START THIS PROJECT: 

The classes Book, BookCollection, CD, CDCollection, DVD, DVDCollection, refer to the GUI windows that will allow operations with the instances of the
objects of this classes, and the definition of the windows and it's behavior.
The classes relative to the user, namely, GetUserRequest, PatchUser, PostUserRequest, SaveUser, SearchUser, UserDetails and UserRepositorySwing,
were constructed to include the nuclear actions for the user.
The classes relative to the shelf, namely, SaveShelf, SearchShelf, ShelfDetails and ShelfRepositorySwing, were constructed to include
the nuclear actions for the shelf.
The StartUpFrame class settles the MainFrame behavior and has the auxiliary classes Help, ImagePanel, Login and Exit.
The Help class is relative to the command use instructions, the ImagePanel class is relative to the image displayed in the MainFrame, the Login class
is responsible for the authentication of the user and the Exit class's responsibility is to close the application.  
The class Book has two parameters in its construction, the ShelfRepository and ElementsRepository, this means that this class depends on the classes 
relative to these two parameters. The class Book has a private class that is responsible for the actions to implement, EventBook, that implements 
ActionListener.
The BookCollection class is similar to the Book class, the difference being the instances of this class are bookCollections, instead of books (the
difference between a book and a book collection is that a book has only one element and book collection has several elements). This class also has a private
class responsible for the actions to implement, EventBookCollection, that implements ActionListener.
The class CD has two parameters in its construction, the ShelfRepository and ElementsRepository, this means that this class depends on the classes 
relative to these two parameters. The class CD has a private class that is responsible for the actions to implement, EventCD, that implements 
ActionListener. 
The CDCollection class is similar to the CD class, the difference being the instances of this class are cdCollections, instead of cds (the
difference between a cd and a cd collection is that a cd has only one element and cd collection has several elements). This class also has a private
class responsible for the actions to implement, EventCDCollection, that implements ActionListener.
The class DVD has two parameters in its construction, the ShelfRepository and ElementsRepository, this means that this class depends on the classes 
relative to these two parameters. The class DVD has a private class that is responsible for the actions to implement, EventDVD, that implements 
ActionListener. 
The DVDCollection class is similar to the DVD class, the difference being the instances of this class are dvdCollections, instead of dvds (the
difference between a dvd and a dvd collection is that a dvd has only one element and dvd collection has several elements). This class also has a private
class responsible for the actions to implement, EventDVDCollection, that implements ActionListener.
The PatchUser class defines how the alterations to the user are made, it has one parameter in its construction, the UserRepository, this means that this
class depends on the class relative to this parameter. The class PatchUser has several inner classes that are responsible for the actions to implement,
EventModelExecuter, that implements ActionListener, the class EventWorker that extends SwingWorker<String, Void> executes a StringWorker thread.
The SaveShelf class defines how the alterations to the Shelf are saved, it has two parameters in its constructor, the Repository and ShelfRepository,
this means that this class depends on the classes relative to these two parameters. The action performed by inner class EventShelfSave, that implements
ActionListener  is made in a background thread, by running SwingWorker framework by executing a EventHandling() object.
The SaveUser class defines how the alterations to the User are saved, it has one parameter, the Repository, this means that this class depends on
the class relative to this parameter. The action performed by inner class EventModelExecuter, that implements ActionListener, is made in a background
thread, running SwingWorker framework by executing a EventHandling() object.
The SearchShelf class







Choose the correct JAR files and execute in command line :
C:\

AUTHORS: Filipa Estiveira; Hugo Leal e Jos� Oliveira


USER INSTRUCTIONS:

- Create a new user: 
StartpUpActivity , select the EDIT - UserManagement - Database -EDIT - NewUser;

- Return users list: 
StartpUpActivity, select the EDIT - UserManagement - Database - EDIT - UserList;

- Return info about a user:
StartpUpActivity, select the EDIT - UserManagement -Database -SEARCH -  byName;

- Creates a new shelf with a Certain dimension(nbElements):
StartpUpActivity , select the EDIT - ShelfManagement -ShelfRepository- EDIT - NewShelf;

- Creates a new element in the indicated(sid - shelf id) Shelf:
StartpUpActivity , select the EDIT - ShelfManagement -AddShelfElement- Element- Book/CD/DVD... ;

- Returns information about all elements of certain shelf(sid - shelf id):
StartpUpActivity , select the EDIT - ShelfManagement -ShelfRepository -GetShelfElements;

- Returns a certain Element(eid) of a certain shelf(sid - shelf id):
StartpUpActivity , select the EDIT - ShelfManagement -ShelfRepository -GetShelfElement;

- Returns GET/shelf/{sid}/elements the details of a shelf(sid - shelf id):
StartpUpActivity , select the EDIT - ShelfManagement -ShelfRepository -GetShelfDetails;

- Returns the details of all shelfs:
StartpUpActivity , select the EDIT - ShelfManagement -ShelfRepository- EDIT - Shelf List;

- Creates an element of type CDCollection:
StartUpActivity, select the EDIT - ShelfManagement - ShelfRepository - ElementsRepository;

- Creates an element of type DVD:
StartUpActivity, select the EDIT - ShelfManagement - ShelfRepository - ElementsRepository;

- Creates an element of type DVDCollection:
StartUpActivity, select the EDIT - ShelfManagement - ShelfRepository - ElementsRepository;

- Returns information about the user's request:
StartUpActivity, select the EDIT - UserManagement - ********


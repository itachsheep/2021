#include<iostream>
using namespace std;


struct Books
{
    int book_id;
    char title[50];

};

int main()
{
    Books book1;
    struct Books *ptr;

    ptr = &book1;

    strcpy(book1.title, " C++ Program");
    book1.book_id = 1001;
    
    cout << "Book title: " << book1.title;
    cout << " id: " << book1.book_id << endl;
    cout << "size: " << sizeof(Books) << endl;
    cout << " book id size: " << sizeof(book1.book_id) << endl;
    cout << " book title size: " << sizeof(book1.title) << endl;

    cout << " *ptr title: " << ptr->title << endl;
    return 0;
}
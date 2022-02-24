# ManyToMany-without-ManyToManyAnnotation

This is many to many relationship but I don't use @ManyToMany annotation.
I used @ManyToOne and @OneToMany association, and added dto layer ;)

You can try this in Postman:
PostReq:
{
    "firstName": "someName",
    "lastName": "someName",
    "roleName": "somePermitions seperate by ','s"
}
Then can call it by id.

My controller gets dto and returns also dto, service also by the same principe.
So, enjoy!

namespace java thrift.generated

typeof i16 short
typeof i32 int
typeof i64 long
typeof bool boolean
typeof string String

struct Person{
  1: optional String username,
  2: optional int age,
  3: optional boolean married
}

exception DataException {
  1: optional String message,
  2: optional String callStack,
  3: optional String data
}


service PersonService {
   Person getPersonByUsername(1: required String username) throws (1: DataException dataException),

   void savePerson(1: required Person person) throws (1: DataException dataException)
}
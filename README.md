# Library Management System #

##### Contributors #####

* Khatia Macharadze
* Nino Basialia
* Anano Bodokia


### Project Structure ###

![alt text](https://github.com/MiniZ/library-management-system/blob/develop/documents/Structure.PNG)


#### Device Communication Protocol ####

```
start_byte|msg_type|msg_status|date|msg_data|crc16
```
| key | type |
| ------ | ------ |
| start_byte | * |
| msg_type | char |
| msg_status | char |
| date | dd/mm/yyyy HH:mm:ss |
| msg_data | n chars depending on msg_type and msg_status |
| CRC16 | checksum |

delimiter between key components : &

```
Message type {
    CHECK_BOOK('b'),
    CHECK_CLIENT('c'),
    SUBMIT('s')
}
```
```
Message status {
    OK('o'),
    ERROR('e'),
    IN_PROGRESS('i')
}
```
```
CHECK_BOOK {
    device ->  *b&i&date&book_id&crc16
               *b&o&date&book name by author@r&crc16 <- server
                                                   OR
               *b&o&date&book name by author@b&crc16 <- server
                                                   OR
               *b&e&date&error_message&crc16         <- server
}
'r' and 'b' defines it is borrow or returning of book
```
```
CHECK_CLIENT {
    device ->  *c&i&date&client_card_id&crc16
               *c&o&date&client name&crc16    <- server
                                              OR
               *c&e&date&error_message&crc16 <- server
}
```
```
SUBMIT {
    device ->  *s&i&date&book_id@client_card_id@date&crc16
               *s&o&date&successfully taken&crc16     <- server
                                                      OR
               *s&o&date&successfully returned&crc16  <- server
                                                      OR
               *s&e&date&error_messsage&crc16 <- server
}
```

##### Process
1. Check book by identifier in system
2. Identify client using RFID
3. Check client on server
4. Sumbit take or return to server

##### CRC algorithm
```
public static String calculateCRC(String input) {
    int result = 0;
    int secret = 9988;
    result += start;
    for (int i = 0; i < input.length(); i++) {
        result += input.charAt(i);
    }
    return Integer.toHexString(0x10000 | result).substring(1).toUpperCase();
}
```

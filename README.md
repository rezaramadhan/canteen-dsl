# DSL Kantin Kelompok 23 K-01
## Anggota Kelompok:
- 13514069 Sekar Anglila H.
- 13514071 Diastuti Utami
- 13514107 M. Reza Ramadhan

## Deskripsi DSL
Mengikuti tugas yang diberikan, kami membuat DSL yang menggambarkan beberapa hal yang biasa terjadi pada kantin. Beberapa hal yang kami coba gambarkan pada DSL ini adalah:

- Pembukaan kantin
- Pengecekan stok yang tersedia
- Pembelian stok baru
- List menu yang ada di kantin tertentu
- Memasak sebuah menu
- Kedatangan seorang customer

### Pembukaan kantin
Pembukaan kantin merupakan fungsi yang dapat pertama kali dipanggil ketika menjalankan DSL. Di dalam fungsi ini kita dapat mendeklarasikan beberapa atribut yang dimiliki kantin seperti kapasitas kantin, uang yang saat ini kantin miliki, stok barang yang ada, dan lain-lain.
Berikut adalah deklarasi yang mungkin diberikan pada fungsi ini:

```
    open{
        capacity <<integer: canteen capacity>>

        has_money <<integer: current canteen's money>>

        current_stocks {
            ...
        }

        buy_stocks <<string: stock name>>, <<integer: stock amount>>
        at_price <<integer: stock price>>

        menu {
            ...
        }

        cooks <<string: cooked menu name>>, <<integer: cooked amount>>

        event_happened {
            ...
        }
    }

```

### Pengecekan stok yang tersedia
Pengecekan stok dimaksudkan sebagai simulasi pengecekan bahan baku yang tersisa dari hari operasional kantin sebelumnya. Dalam pengecekan stok pengguna dapat menyatakan bahwa sebuah barang masih siap digunakan atau sudah tidak layak dipakai karena basi atau kadaluarsa.
Berikut adalah deklarasi yang mungkin diberikan pada fungsi ini:
```
    current_stocks {
        ready <<string: stock name>>, <<integer: stock amount>>
        expired_food <<string: stock name>>, <<integer: stock amount>>
    }
```

### Pembelian stok baru
Pembelian stok dilakukan untuk menambah item yang ada di stok makanan. Selain itu, pembelian stok mengakibatkan uang yang ada di kantin berkurang.
Berikut adalah deklarasi yang mungkin diberikan pada fungsi ini:
```
    buy stocks <<string: stock name>>, <<integer: stock name>>
```

### List menu yang ada di kantin tertentu
Bagian ini dikhususkan untuk user melakukan list dari menu apa saja yang saat ini ada di kantin itu. Bagian ini juga menyatakan apa saja bahan baku dari sebuah menu dan harga jual satuan dari menu tersebut.
Berikut adalah deklarasi yang mungkin diberikan pada fungsi ini:
```
    menu {
        avaiable_menu {
            menu_name <<string>>
            ingredient <<string: stock name>>, <<integer: stock amount needed>>
            price <<integer: harga jual>>
        }
    }
```

### Memasak sebuah menu
Memasak menu dilakukan untuk membuat sebuah item yang ada di daftar menu dapat dipesan oleh pelanggan. Pada proses ini, jumlah makanan yang tersedia akan bertambah dan jumlah bakan baku akan berkurang.
Berikut adalah deklarasi yang mungkin diberikan pada fungsi ini:
```
    cooks <<string: nama item>>, <<integer: jumlah yang dimasak>>
```

### Kedatangan seorang customer
Kedatangan pelanggan digambarkan sebagai sebuah event yang mungkin terjadi berkali-kali di sebuah kantin. Pada bagian ini user dapat menggambarkan pesanan pelanggan, apakah ia makan di tempat atau tidak, serta pembayaran yang terjadi.
Berikut adalah deklarasi yang mungkin diberikan pada fungsi ini:
```
    event_happened {
        customer_is_coming {
            he_ordered {
                menu <<string: nama item yang dipesan>>, <<integer: jumlah>>
                take_away() / dine_in_for <<integer: jumlah orang yang makan>>
            }
            paid <<integer: jumlah uang yang dibayarkan>>
        }
    }
```
## Petunjuk kompilasi dan menjalankan program
Kami telah membuat contoh penggunaan dsl pada file dsl-usage.groovy.
Untuk melakukan kompilasi pada groovy, jalankan perintah berikut.
```
    groovyc CanteenDSL.groovy FoodStockDSL.groovy MenuDSL.groovy CustomerDSL.groovy
```

Kemudian, jalankan perintah berikut untuk melihat contoh penggunaan dsl.
```
    groovy -cp . dsl-usage.groovy
```

# Foobar

Kartaca Çekirdekten Yetişenler Programı için yapmış olduğum proje.

User Registration yapılabilir.

Angular cli sürümü 7.2.0'dır. Daha yüksek sürümlerde hata alınırsa sürüm düşmeyi belirttim.

Kafka kullanılmıştır. Hangi kullanıcının hangi gezi bilgisine baktığı ve baktığı gezi bilgileri 'fatihmayuk.log' belgesi içinde loglanmıştır.

Docker kullanılmıştır. İmajı nasıl ayağa kaldıracağınızı belirttim.

Docker imajı maven plugin ile oluşturulabilir. Maven sekmesi altında

Swagger Dokumantasyonu için konfigurasyonlar yapılmıştır.

Spring Security-JWT kullanılmıştır.


## Installation

İlgili klasörler içinde çalıştırılacak komutlar:

```bash
docker-compose -f docker-compose.yml up -d
```
```bash
npm install
```
```bash
npm start
```
```bash
npm uninstall -g @angular/cli
npm cache clean
npm install -g @angular/cli@7.2.0
```

## License
[fatihmyk](https://github.com/fatihmyk)
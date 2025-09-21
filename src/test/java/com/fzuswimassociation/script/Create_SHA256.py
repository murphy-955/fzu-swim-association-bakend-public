import hashlib

def create_sha256(string,salt):
    sha256 = hashlib.sha256()
    salted_string = string + salt
    sha256.update(salted_string.encode('utf-8'))
    return sha256.hexdigest()

print(create_sha256("123456a","duvobfeilvbioheyuvgosy"))
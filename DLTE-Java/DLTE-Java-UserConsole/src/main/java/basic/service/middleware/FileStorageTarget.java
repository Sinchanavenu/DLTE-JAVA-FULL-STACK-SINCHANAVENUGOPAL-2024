package basic.service.middleware;

import basic.service.remotes.StorageTarget;
import basic.service.remotes.UserDetailsRepository;

public class FileStorageTarget implements StorageTarget {
    @Override
    public UserDetailsRepository getUserDetailsRepository() {
        return new UserDetailsFileRepository("mybank-userdetails.doc");
    }
}

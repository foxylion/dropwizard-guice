Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/wily64"
  config.vm.provision "shell", inline: "apt-get install -y openjdk-8-jdk maven git ruby-dev"
  config.vm.provision "shell", inline: "gem install travis"
end

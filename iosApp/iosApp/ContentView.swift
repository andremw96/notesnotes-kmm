import SwiftUI
import shared

struct ContentView: View {
    let greet = IOSPlatform.init().name

	var body: some View {
		LoginScreen()
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

import SwiftUI
import shared

struct ContentView: View {
    let greet = IOSPlatform.init().name

	var body: some View {
		Text("hello " + greet)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

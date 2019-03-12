Class {
	#name : #MMCMooseFormPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'formManager'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseFormPresenter class >> defaultSpec [
	^ SpecLayout composed
		add: #formManager;
		yourself
]

{ #category : #specs }
MMCMooseFormPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #accessing }
MMCMooseFormPresenter >> formManager [
	^ formManager
]

{ #category : #initialization }
MMCMooseFormPresenter >> initializeWidgets [
	formManager := self newTabManager.
	formManager
		addTab: (self pageFor: MMCMoosePropertyFormPresenter);
		addTab: (self pageFor: MMCMooseAssociationFormPresenter);
		addTab: (self pageFor: MMCMooseNavigationFormPresenter)
]

{ #category : #utilities }
MMCMooseFormPresenter >> pageFor: aFormClass [
	| aForm |
	aForm := self instantiate: aFormClass.
	aForm subscribeTo:context . 
	^ self newTab
		label: aForm name;
		closeable: false;
		presenter: aForm;
		yourself
]
